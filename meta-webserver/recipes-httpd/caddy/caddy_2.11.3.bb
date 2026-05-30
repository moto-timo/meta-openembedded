SUMMARY = "Fast and extensible multi-platform HTTP/1-2-3 web server with automatic HTTPS"
DESCRIPTION = "Caddy is a powerful, enterprise-ready, open source web server with \
automatic HTTPS written in Go. It can serve static files, act as a reverse proxy, \
and obtain and renew TLS certificates automatically."
HOMEPAGE = "https://caddyserver.com"
BUGTRACKER = "https://github.com/caddyserver/caddy/issues"
SECTION = "net"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/${GO_IMPORT}/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "git://github.com/caddyserver/caddy.git;protocol=https;branch=master;destsuffix=${GO_SRCURI_DESTSUFFIX} \
           file://Caddyfile \
           file://caddy.service \
           file://run-ptest \
           "
SRCREV = "28a44fbb85e90080b31974b9b0ef7faf64b9e24b"
require ${BPN}-go-mods.inc

GO_IMPORT = "github.com/caddyserver/caddy/v2"
GO_INSTALL = "${GO_IMPORT}/cmd/caddy"
GO_LINKSHARED = ""

inherit go-mod go-mod-update-modules systemd ptest

# Packages whose Go tests are cross-compiled, installed and run on target.
# These are self-contained (no network access required at test time).
GO_TEST_PACKAGES ?= "${GO_IMPORT}/caddyconfig/caddyfile \
                     ${GO_IMPORT}/caddyconfig/httpcaddyfile \
                     ${GO_IMPORT}/modules/caddyhttp/headers \
                    "

do_compile_ptest() {
    cd ${B}/src/${GO_IMPORT}
    mkdir -p ${B}/ptest
    for pkg in ${GO_TEST_PACKAGES}; do
        out=$(echo $pkg | tr '/' '-')
        ${GO} test ${GOBUILDFLAGS} -c -o ${B}/ptest/${out}.test $pkg
    done
}

do_install_ptest() {
    install -d ${D}${PTEST_PATH}/tests
    install -m 0755 ${B}/ptest/*.test ${D}${PTEST_PATH}/tests/
}

SYSTEMD_SERVICE:${PN} = "caddy.service"
SYSTEMD_AUTO_ENABLE = "disable"

do_install:append() {
    # Default configuration file
    install -d ${D}${sysconfdir}/caddy
    install -m 0644 ${UNPACKDIR}/Caddyfile ${D}${sysconfdir}/caddy/Caddyfile

    # systemd unit
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${UNPACKDIR}/caddy.service ${D}${systemd_system_unitdir}/caddy.service

    # State and site directories used by the service unit
    install -d ${D}${localstatedir}/lib/caddy
    install -d ${D}${localstatedir}/www/html
}

# Create a dedicated caddy user/group for the service
inherit useradd
USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = "--system --no-create-home --home-dir ${localstatedir}/lib/caddy \
                       --shell /sbin/nologin --user-group caddy"

FILES:${PN} += "${sysconfdir}/caddy ${localstatedir}/lib/caddy ${localstatedir}/www"

# mark these as src, since there are bash scripts etc in there and QA will complain otherwise
FILES:${PN}-src += "${libdir}/go/src"
INSANE_SKIP:${PN}-dev = "file-rdeps"

# Statically linked Go test binaries: the .gnu_hash section serves no purpose
# and ldflags QA check does not apply.
INSANE_SKIP:${PN}-ptest += "ldflags"
