SUMMARY = "nose extends unittest to make testing easier - python3 version"
DESCRIPTION = "nose extends the test loading and running features of unittest, \
making it easier to write, find and run tests."
HOMEPAGE = "https://github.com/atsb/nose-py3"
BUGTRACKER = ""
SECTION = "devel/python"

LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE_python3-nose-py3;md5=1803fa9c2c3ce8cb06b4861d75310742"

SRC_URI += "https://raw.githubusercontent.com/atsb/nose-py3/19a239ad5bd7f6eb782b9ee7be5e6b94651fdb9a/LICENSE;name=license;downloadfilename=LICENSE_python3-nose-py3"
SRC_URI[sha256sum] = "560de1e49a1dbd677ff5b2858c683bba32345d468c962e534fb2e5830d3d24ed"
SRC_URI[license.sha256sum] = "20c17d8b8c48a600800dfd14f95d5cb9ff47066a9641ddeab48dc54aec96e331"

S = "${WORKDIR}/nose-py3-${PV}"

inherit pypi setuptools3

RDEPENDS:${PN} += "python3-core python3-six"

RDEPENDS:${PN} = "\
  python3-unittest \
  "

PROVIDES += "python3-nose"

BBCLASSEXTEND = "native nativesdk"
