SUMMARY = "File path utility"
DESCRIPTION = "This module provides a small, fast utility for working with \
file paths. It is friendlier to use than File::Spec and provides easy \
access to functions from several other core file handling modules. It aims \
to be smaller and faster than many alternatives on CPAN, while helping \
people do many common things in consistent and less error-prone ways."
HOMEPAGE = "ihttps://github.com/dagolden/Path-Tiny"
BUGTRACKER = "https://github.com/dagolden/Path-Tiny/issues"
CHANGELOG = "https://metacpan.org/dist/Path-Tiny/changes"
SECTION = "libs"

LICENSE = "Artistic-1.0 | GPL-1.0+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0d4be4fba9b7d7c16379d54e87d199f1"

SRC_URI = "${CPAN_MIRROR}/authors/id/D/DA/DAGOLDEN/Path-Tiny-${PV}.tar.gz"
SRC_URI[sha256sum] = "4bc6f76d0548ccd8b38cb66291a885bf0de453d0167562c7b82e8861afdcfb7c"

S = "${WORKDIR}/Path-Tiny-${PV}"

inherit cpan ptest-perl

BBCLASSEXTEND = "native nativesdk"
