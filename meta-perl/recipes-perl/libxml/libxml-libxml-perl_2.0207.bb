SUMMARY = "Perl interface to the libxml2 library"
DESCRIPTION = "This module is an interface to libxml2, providing XML and HTML parsers \
with DOM, SAX and XMLReader interfaces, a large subset of DOM Layer 3 \
interface and a XML::XPath-like interface to XPath API of libxml2. \
The module is split into several packages which are not described in this \
section; unless stated otherwise, you only need to use XML::LibXML; in \
your programs."

HOMEPAGE = "https://metacpan.org/dist/XML-LibXML/view/LibXML.pod"
SECTION = "libs"
LICENSE = "Artistic-1.0|GPLv1+"
DEPENDS += "\
	libalien-build-perl-native \
	libalien-libxml2-perl-native \
        libxml-sax-perl-native \
        libxml2 \
        zlib \
"
RDEPENDS:${PN} += "\
    libxml2 \
    libxml-namespacesupport-perl \
    libxml-sax-perl \
    libxml-sax-base-perl \
    perl-module-carp \
    perl-module-data-dumper \
    perl-module-dynaloader \
    perl-module-encode \
    perl-module-encode-encoding \
    perl-module-exporter \
    perl-module-io-handle \
    perl-module-scalar-util \
    perl-module-symbol \
    perl-module-tie-hash \
    zlib \
"
SRC_URI = "${CPAN_MIRROR}/authors/id/S/SH/SHLOMIF/XML-LibXML-${PV}.tar.gz;name=libxml \
           file://fix-CATALOG-conditional-compile.patch \
           file://using-DOCB-conditional.patch \
           "
LIC_FILES_CHKSUM = "file://debian/copyright;md5=64eda1bc135f0ece1d1187f2a8ac82c1 \
    file://LICENSE;md5=97871bde150daeb5e61ad95137ff2446 \
"
SRC_URI[libxml.sha256sum] = "903436c9859875bef5593243aae85ced329ad0fb4b57bbf45975e32547c50c15"

S = "${WORKDIR}/XML-LibXML-${PV}"

inherit cpan ptest-perl

EXTRA_CPANFLAGS = "INC=-I${STAGING_INCDIR}/libxml2 LIBS=-L${STAGING_LIBDIR}"

BBCLASSEXTEND = "native"

CFLAGS += " -D_GNU_SOURCE "
BUILD_CFLAGS += " -D_GNU_SOURCE "

FILES:${PN}-dbg =+ "${libdir}/perl/vendor_perl/*/auto/XML/LibXML/.debug/"

RDEPENDS:${PN}-ptest += " \
    liburi-perl \
    perl-module-encode-byte \
    perl-module-encode-unicode \
    perl-module-io-file \
    perl-module-locale \
    perl-module-list-util \
    perl-module-perlio-scalar \
    perl-module-test-more \
    perl-module-test2-api \
    perl-module-test2-api-instance \
    perl-module-test2-util \
"

do_install:prepend() {
	# test requires "-T" (taint) command line option
	rm -rf ${B}/t/pod.t
	# this only applies to author build
	rm -rf ${B}/t/pod-files-presence.t
}

do_install_ptest() {
	cp -r ${B}/t/data ${D}${PTEST_PATH}/t/
	cp -r ${B}/t/lib ${D}${PTEST_PATH}/t/
	cp -r ${B}/example ${D}${PTEST_PATH}
	cp -r ${B}/test ${D}${PTEST_PATH}
	chown -R root:root ${D}${PTEST_PATH}
}
