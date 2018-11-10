SUMMARY = "Phoronix Test Suite"
DESCRIPTION = "The Phoronix Test Suite is designed to carry out both qualitative \
and quantitative benchmarks in a clean, reproducible, and easy-to-use manner."
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"
SECTION = "console/tests"

SRC_URI = "http://www.phoronix-test-suite.com/releases/${BP}.tar.gz"
SRC_URI_append_class-native = " file://0001-pts-core.php-use-STAGING_DIR_TARGET.patch"
SRC_URI[md5sum] = "33e7b9ba5a65e7fcef91d47117450e6c"
SRC_URI[sha256sum] = "d7160df5ab15fbd65ade82c6cc13c02a4826d46eff10113f615e38316c9c01f1"

S = "${WORKDIR}/phoronix-test-suite"

inherit systemd

do_install_class-target() {
    DESTDIR=${D} ./install-sh ${exec_prefix}

    if [ "${systemd_unitdir}" != "/usr/lib/systemd" ]; then
        install -d ${D}/${systemd_unitdir}/system/
        mv ${D}/usr/lib/systemd/system/* ${D}/${systemd_unitdir}/system/
        rm -rf ${D}/usr/lib/
    fi
}

do_install_class-native() {
    DESTDIR=${D} ./install-sh ${exec_prefix}

    # Add a symlink to the native phoronix-test-suite so that scripts can just invoke
    # "nativepts" and get the right one without needing absolute paths
    # (these often end up too long for the #! parser in the kernel as the
    # buffer is 128 bytes long).
    ln -s phoronix-test-suite ${D}${bindir}/nativepts
}

# FIXME: if ~/.phoronix-test-suite exists, it will be used
#do_install_append_class-native() {
#    sed -i 's:~/.phoronix-test-suite:${D}/${datadir}/.phoronix-test-suite:g' ${D}/${datadir}/${BPN}/pts-core/static/user-config-defaults.xml
#}

# should only be needed if we are not running with fakeroot
#export PTS_USER_PATH_OVERRIDE = '${D}${servicedir}/'

#do_install_append_class-target() {
#    mkdir ${D}${servicedir}
#}

fakeroot python do_make_openbenchmarking_cache () {
    import os
    import subprocess
    import signal

    def subprocess_setup():
        # Python installs a SIGPIPE handler by default. This is usually not what
        # non-Python subprocesses expect.
        # SIGPIPE errors are known issues with gzip/bash
        signal.signal(signal.SIGPIPE, signal.SIG_DFL)

    # need to export for phoronix-test-suite usage
    os.environ['STAGING_DIR_TARGET'] = d.expand('${STAGING_DIR_TARGET}')
    bb.note(os.environ['STAGING_DIR_TARGET'])
    pts = d.expand('${STAGING_BINDIR_NATIVE}/phoronix-test-suite')
    cmd = "%s make-openbenchmarking-cache" % pts
    ret = subprocess.call(cmd, preexec_fn=subprocess_setup, shell=True)
    if ret != 0:
        raise Exception("cmd: %s\n\nMaking OpenBenchmarking cache for phoronix-test-suite failed with return value %s" % (cmd, ret))
}
addtask do_make_openbenchmarking_cache

PTS_INSTALL_TESTS = "cyclictest gputest xsbench"

fakeroot python do_make_download_cache () {
    import os
    import subprocess
    import signal

    def subprocess_setup():
        # Python installs a SIGPIPE handler by default. This is usually not what
        # non-Python subprocesses expect.
        # SIGPIPE errors are known issues with gzip/bash
        signal.signal(signal.SIGPIPE, signal.SIG_DFL)

    # need to export for phoronix-test-suite usage
    os.environ['STAGING_DIR_TARGET'] = d.expand('${STAGING_DIR_TARGET}')
    #pts = d.expand('${STAGING_BINDIR_NATIVE}/phoronix-test-suite')
    pts = 'nativepts'
    test_packages = d.getVar('PTS_INSTALL_TESTS').split()
    cmd = "%s enterprise-setup" % pts
    ret = subprocess.call(cmd, preexec_fn=subprocess_setup, shell=True)
    if ret != 0:
        raise Exception("cmd: %s\n\nEnterprise setup failed for phoronix-test-suite with return value %s" % (cmd, ret))
    for test in test_packages:
        cmd = "%s make-download-cache %s" % (pts, test)
        bb.note(cmd)
        #cmd = "%s" % pts
        # TODO: need to test if terms of use have been accepted
        ret = subprocess.call(cmd, preexec_fn=subprocess_setup, shell=True)
        if ret != 0:
            raise Exception("cmd: %s\n\nmake-download-cache of Phoronix test suite %s failed with return value %s" % (cmd, test, ret))

    bb.build.exec_func('do_install_download_cache', d)
}
addtask do_make_download_cache before do_package after do_install

fakeroot do_install_download_cache() {
    install -d -m 755 ${D}${localstatedir}/lib/${BPN}
    install -d -m 755 ${D}${localstatedir}/cache/${BPN}
    cp -R ${STAGING_DIR_TARGET}${localstatedir}/lib/${BPN}/download-cache ${D}${localstatedir}/lib/${BPN}/
    cp -R ${STAGING_DIR_TARGET}${localstatedir}/cache/${BPN}/openbenchmarking.org ${D}${localstatedir}/cache/${BPN}
    chown -R root:root ${D}
}

fakeroot python do_download_test_files () {
    import os
    import subprocess
    import signal

    def subprocess_setup():
        # Python installs a SIGPIPE handler by default. This is usually not what
        # non-Python subprocesses expect.
        # SIGPIPE errors are known issues with gzip/bash
        signal.signal(signal.SIGPIPE, signal.SIG_DFL)

    bb.build.exec_task('do_build_sysroot_native', d)

    # need to export for phoronix-test-suite usage
    os.environ['STAGING_DIR_TARGET'] = d.expand('${STAGING_DIR_TARGET}')
    pts = d.expand('${STAGING_BINDIR_NATIVE}/phoronix-test-suite')
    test_packages = d.getVar('PTS_INSTALL_TESTS').split()
    cmd = "%s enterprise-setup" % pts
    ret = subprocess.call(cmd, preexec_fn=subprocess_setup, shell=True)
    if ret != 0:
        raise Exception("cmd: %s\n\nEnterprise setup failed for phoronix-test-suite with return value %s" % (cmd, ret))
    for test in test_packages:
        cmd = "%s download-test-files %s" % (pts, test)
        #cmd = "%s" % pts
        # TODO: need to test if terms of use have been accepted
        ret = subprocess.call(cmd, preexec_fn=subprocess_setup, shell=True)
        if ret != 0:
            raise Exception("cmd: %s\n\nDownload of Phoronix test file %s failed with return value %s" % (cmd, test, ret))

    bb.build.exec_func('do_install_test_files', d)
}
addtask do_download_test_files

fakeroot do_install_test_files() {
    install -d -m 755 ${D}${localstatedir}/lib/${BPN}
    install -d -m 755 ${D}${localstatedir}/cache/${BPN}
    cp -R ${STAGING_DIR_TARGET}${localstatedir}/lib/${BPN}/test-profiles ${D}${localstatedir}/lib/${BPN}
    cp -R ${STAGING_DIR_TARGET}${localstatedir}/lib/${BPN}/test-suites ${D}${localstatedir}/lib/${BPN}
    cp -R ${STAGING_DIR_TARGET}${localstatedir}/cache/${BPN}/openbenchmarking.org ${D}${localstatedir}/cache/${BPN}
    #chmod 0644 ${D}${localstatedir}/lib/${BPN}/*/*/*/*.xml
    #chmod 0644 ${D}${localstatedir}/lib/${BPN}/*/*/*/*.zip
    #chmod 0644 ${D}${localstatedir}/cache/${BPN}/*/*/*.xml
    #chmod 0644 ${D}${localstatedir}/cache/${BPN}/*/*/*.zip
    chown -R root:root ${D}
}

# It is not advisable to enable these services by default since they can cause
# continual target reboots if they encounter network problems.
#
SYSTEMD_AUTO_ENABLE = "disable"
SYSTEMD_SERVICE_${PN} = "phoromatic-client.service phoromatic-server.service"

DEPENDS = "php-native"
DEPENDS_append_class-target = " phoronix-test-suite-native zlib-native"

RDEPENDS_${PN} += "bash gcc python php-cli util-linux-lscpu os-release lsb"
# These do not have -native packages
RDEPENDS_${PN}_remove_class-native = "gcc os-release lsb util-linux-lscpu"

pkg_postinst_ontarget() {
    phoronix-test-suite enterprise-setup
    phoronix-test-suite install ${PTS_INSTALL_TESTS}
}

FILES_${PN} += " \
    ${datadir}/phoronix-test-suite \
    ${datadir}/appdata/phoronix-test-suite.appdata.xml \
    ${datadir}/icons/hicolor/48x48/apps/phoronix-test-suite.png \
    ${datadir}/icons/hicolor/64x64/mimetypes/application-x-openbenchmarking.png \
    ${datadir}/mime/packages/openbenchmarking-mime.xml \
    ${systemd_unitdir}/* \
    ${localstatedir}/lib/${BPN}/* \
    ${localstatedir}/cache/${BPN}/* \
"

BBCLASSEXTEND = "native"
