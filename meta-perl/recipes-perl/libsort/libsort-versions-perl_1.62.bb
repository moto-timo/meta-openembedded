SUMMARY = "Sort::Versions - a perl 5 module for sorting of revision-like numbers"
DESCRIPTION = "Sort::Versions allows easy sorting of mixed non-numeric and \
numeric strings, like the 'version numbers' that many shared library systems \
and revision control packages use. This is quite useful if you are trying \
to deal with shared libraries. It can also be applied to applications that \
intersperse variable-width numeric fields within text. Other applications \
can undoubtedly be found."
HOMEPAGE = "ihttps://github.com/neilb/Sort-Versions"
BUGTRACKER = "https://rt.cpan.org/Public/Dist/Display.html?Name=Sort-Versions"
CHANGELOG = "https://metacpan.org/dist/Sort-Versions/changes"
SECTION = "libs"

LICENSE = "Artistic-1.0 | GPL-1.0+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=353ffda9d94b91b1538bc611db80e166"

SRC_URI = "https://cpan.metacpan.org/authors/id/N/NE/NEILB/Sort-Versions-${PV}.tar.gz"
SRC_URI[sha256sum] = "bf5f3307406ebe2581237f025982e8c84f6f6625dd774e457c03f8994efd2eaa"

S = "${WORKDIR}/Sort-Versions-${PV}"

inherit cpan ptest-perl

BBCLASSEXTEND = "native nativesdk"
