SUMMARY = "Mycroft Skill Manager, in python!"
HOMEPAGE = "https://github.com/MycroftAI/mycroft-skills-manager"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e23fadd6ceef8c618fc1c65191d846fa"

SRC_URI[sha256sum] = "3fbed593800aa669f0af2f01edfc3b54de46e995e0bdf03c5ea3693bdeab8735"

inherit pypi setuptools3

RDEPENDS:${PN} += "\
    python3-pako \
    python3-monotonic \
    python3-appdirs \
"

do_install:append() {
    # Stop this from being installed
    rm -rf ${D}/usr/share
}
