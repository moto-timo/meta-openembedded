SUMMARY = "A libudev binding"

LICENSE = "LGPLv2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=a6f89e2100d9b6cdffcea4f398e37343"

SRC_URI[sha256sum] = "1512e5504cb7effd4c2903c9d0450ff92dfc3a2883c411a86b804a2f9a63f8c1"

inherit pypi setuptools3

do_configure:prepend() {
    sed -i "/import pyudev/d" ${S}/setup.py
    sed -i "s/str(pyudev.__version__)/'${PV}'/g" ${S}/setup.py
}

RDEPENDS:${PN} = "\
    ${PYTHON_PN}-ctypes \
    ${PYTHON_PN}-misc \
    ${PYTHON_PN}-six \
    ${PYTHON_PN}-threading \
    ${PYTHON_PN}-fcntl \
    libudev \
"

BBCLASSEXTEND = "native nativesdk"
