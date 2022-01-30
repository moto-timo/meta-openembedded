SUMMARY = "Perl implementation of the which utility as an API"
DESCRIPTION = "File::Which finds the full or relative paths to executable \
programs on the system. This is normally the function of which utility. \
which is typically implemented as either a program or a built in shell \
command. On some platforms, such as Microsoft Windows it is not provided \
as part of the core operating system. This module provides a consistent \
API to this functionality regardless of the underlying platform."
HOMEPAGE = "https://metacpan.org/pod/File::Which"
BUGTRACKER = "https://github.com/uperl/File-Which/issues"
CHANGELOG = "https://metacpan.org/dist/File-Which/changes"
SECTION = "libs"

LICENSE = "Artistic-1.0 | GPL-1.0+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d4aece164a4cb5012c03e0b99aa7c6a4"

SRC_URI = "${CPAN_MIRROR}/authors/id/P/PL/PLICEASE/File-Which-${PV}.tar.gz"
SRC_URI[sha256sum] = "3201f1a60e3f16484082e6045c896842261fc345de9fb2e620fd2a2c7af3a93a"

S = "${WORKDIR}/File-Which-${PV}"

inherit cpan ptest-perl

BBCLASSEXTEND = "native nativesdk"
