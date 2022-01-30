SUMMARY = "Mojo - Web development toolkit"
DESCRIPTION = "A powerful web development toolkit, with all the basic \
tools and helpers needed to write simple web applications and higher \
level web frameworks, such as Mojolicious. Some of the most commonly used \
tools are Mojo::UserAgent, Mojo::DOM, Mojo::JSON, Mojo::Server::Daemon, \
Mojo::Server::Prefork, Mojo::IOLoop and Mojo::Template."
HOMEPAGE = "https://mojolicious.org/"
BUGTRACKER = "https://github.com/mojolicious/mojo/issues"
CHANGELOG = "https://metacpan.org/dist/Mojolicious/changes"
SECTION = "libs"

LICENSE = "Artistic-1.0 | GPL-1.0+"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e25cec8106f18dc981c1f6a3609df057"

SRC_URI = "${CPAN_MIRROR}/authors/id/S/SR/SRI/Mojolicious-${PV}.tar.gz"
SRC_URI[sha256sum] = "6b76e024d3d6c077ad984ea3b3d229f093373d5e06438aca248235150d029043"

S = "${WORKDIR}/Mojolicious-${PV}"

inherit cpan ptest-perl

BBCLASSEXTEND = "native nativesdk"
