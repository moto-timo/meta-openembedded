SUMMARY = "File::chdir - a more sensible way to change directories"
DESCRIPTION = "Perl's chdir() has the unfortunate problem of being very, \
very, very global. If any part of your program calls chdir() or if any \
library you use calls chdir(), it changes the current working directory \
for the *whole* program.\
\
This sucks.\
\
File::chdir gives you an alternative, $CWD and @CWD. These two variables \
combine all the power of chdir(), File::Spec and Cwd."
HOMEPAGE = "ihttps://github.com/dagolden/File-chdir"
BUGTRACKER = "https://github.com/dagolden/File-chdir/issues"
CHANGELOG = "https://metacpan.org/dist/File-chdir/changes"
SECTION = "libs"

LICENSE = "Artistic-1.0 | GPL-1.0+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=17a846aa9360ded559404b8cad9f0fe9"

SRC_URI = "${CPAN_MIRROR}/authors/id/D/DA/DAGOLDEN/File-chdir-${PV}.tar.gz"
SRC_URI[sha256sum] = "efc121f40bd7a0f62f8ec9b8bc70f7f5409d81cd705e37008596c8efc4452b01"

S = "${WORKDIR}/File-chdir-${PV}"

inherit cpan ptest-perl

BBCLASSEXTEND = "native nativesdk"
