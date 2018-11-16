SUMMARY = "Phoronix Test Suite Tests"
DESCRIPTION = "The Phoronix Test Suite is designed to carry out both qualitative \
and quantitative benchmarks in a clean, reproducible, and easy-to-use manner."
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"
SECTION = "console/tests"

# FIXME: if ~/.phoronix-test-suite exists, it will be used
#do_install_append_class-native() {
#    sed -i 's:~/.phoronix-test-suite:${D}/${datadir}/.phoronix-test-suite:g' ${D}/${datadir}/${BPN}/pts-core/static/user-config-defaults.xml
#}

# should only be needed if we are not running with fakeroot
#export PTS_USER_PATH_OVERRIDE = '${D}${servicedir}/'

DEPENDS = "phoronix-test-suite-native phoronix-test-suite"

python do_fetch () {
    if d.getVar('PTS_INSTALL_TESTS'):
        bb.build.exec_func('do_make_download_cache', d)
}

fakeroot python do_make_openbenchmarking_cache () {
    import os
    import subprocess
    import signal
    from bb.fetch2 import runfetchcmd

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

fakeroot python do_make_download_cache () {
    import os
    import subprocess
    import signal
    from bb.fetch2 import runfetchcmd

    def subprocess_setup():
        # Python installs a SIGPIPE handler by default. This is usually not what
        # non-Python subprocesses expect.
        # SIGPIPE errors are known issues with gzip/bash
        signal.signal(signal.SIGPIPE, signal.SIG_DFL)

    # need to export for phoronix-test-suite usage
    os.environ['STAGING_DIR_TARGET'] = d.expand('${STAGING_DIR_TARGET}')
    pts = d.expand('${STAGING_BINDIR_NATIVE}/phoronix-test-suite')
    test_packages = d.getVar('PTS_INSTALL_TESTS').split()
    cmd = "%s enterprise-setup" % pts
    #ret = subprocess.call(cmd, preexec_fn=subprocess_setup, shell=True)
    ret = runfetchcmd(cmd, d)
    if ret != 0:
        raise Exception("cmd: %s\n\nEnterprise setup failed for phoronix-test-suite with return value %s" % (cmd, ret))
    for test in test_packages:
        cmd = "%s make-download-cache %s" % (pts, test)
        #cmd = "%s" % pts
        # TODO: need to test if terms of use have been accepted
        ret = subprocess.call(cmd, preexec_fn=subprocess_setup, shell=True)
        if ret != 0:
            raise Exception("cmd: %s\n\nmake-download-cache of Phoronix test suite %s failed with return value %s" % (cmd, test, ret))

    bb.build.exec_func('do_install_download_cache', d)
}
addtask do_make_download_cache

fakeroot do_install_download_cache() {
    install -d -m 755 ${D}${datadir}/${BPN}
    install -d -m 755 ${D}${datadir}/${BPN}/download-cache
    cp -R ${STAGING_DIR_TARGET}${datadir}/${BPN}/download-cache ${D}${datadir}/${BPN}/
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

RDEPENDS_${PN} += "phoronix-test-suite"

FILES_${PN} += " \
    ${localstatedir}/lib/${BPN}/* \
    ${localstatedir}/cache/${BPN}/* \
    ${datadir}/${BPN}/download-cache/* \
"
