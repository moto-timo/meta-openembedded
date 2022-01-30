SUMMARY = "Install the C libxml2 library on your system"
DESCRIPTION = "This module provides libxml2 for other modules to use."
HOMEPAGE = "https://metacpan.org/pod/Alien::Libxml2"
BUGTRACKER = "https://github.com/PerlAlien/Alien-Libxml2/issues"
CHANGELOG = "https://metacpan.org/dist/Alien-Libxml2/changes"
SECTION = "libs"
LICENSE = "Artistic-1.0 | GPL-1.0+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0ea58e73b7771aafa5899507b5905adb"

SRC_URI = "${CPAN_MIRROR}/authors/id/P/PL/PLICEASE/Alien-Libxml2-${PV}.tar.gz"
SRC_URI[sha256sum] = "73b45244f0b5c36e5332c33569b82a1ab2c33e263f1d00785d2003bcaec68db3"

S = "${WORKDIR}/Alien-Libxml2-${PV}"

DEPENDS += "libxml2 libalien-build-perl-native"

inherit cpan ptest-perl pkgconfig

BBCLASSEXTEND = "native nativesdk"
