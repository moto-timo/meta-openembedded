SUMMARY = "Build external dependencies for use in CPAN"
DESCRIPTION = "This module provides tools for building external (non-CPAN) \
dependencies for CPAN. It is mainly designed to be used at install time of \
a CPAN client, and work closely with Alien::Base which is used at runtime."
HOMEPAGE = "https://metacpan.org/pod/Alien::Build"
BUGTRACKER = "https://github.com/PerlAlien/Alien-Build/issues"
CHANGELOG = "https://metacpan.org/dist/Alien-Build/changes"

LICENSE = "Artistic-1.0 | GPLv1+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=66b5596c0a3edbc0a7badbce941fe202"

SRC_URI = "https://cpan.metacpan.org/authors/id/P/PL/PLICEASE/Alien-Build-${PV}.tar.gz"
SRC_URI[sha256sum] = "4bb301fa2b6296a9d8e285000932ea3340832a3a70bdb9c5e1a63c6fd1cf4a67"

S = "${WORKDIR}/Alien-Build-${PV}"

DEPENDS += "\
    libfile-chdir-perl-native \
    libfile-which-perl-native \
    libpath-tiny-perl-native \
    liburi-perl-native \
"

inherit cpan ptest-perl

RDEPENDS:${PN} += " \
    libcapture-tiny-perl \
    libfile-chdir-perl \
    libfile-which-perl \
    libmojolicious-perl \
    libpath-tiny-perl \
    libsort-versions-perl \
    liburi-perl \
"

BBCLASSEXTEND = "native nativesdk"
