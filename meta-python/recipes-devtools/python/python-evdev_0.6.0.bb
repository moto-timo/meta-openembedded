SUMMARY = "Python evdev lib"
HOMEPAGE = "https://github.com/gvalkov/python-evdev"
SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=18debddbb3f52c661a129724a883a8e2"


SRC_URI[md5sum] = "294ac2997bd419d56b9451511536f9f4"
SRC_URI[sha256sum] = "c0e1410cc88eaa6a016baeafb2acb1274d36a057944143b59e94f36bb4aaaa82"

DEPENDS_${PN} += "\
    ${PYTHON_PN}-ctypes \
"

inherit pypi setuptools

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-ctypes \
    ${PYTHON_PN}-fcntl \
    ${PYTHON_PN}-io \
    ${PYTHON_PN}-shell \
    ${PYTHON_PN}-stringold \
"
