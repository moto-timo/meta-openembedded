# FIXME: the LIC_FILES_CHKSUM values have been updated by 'devtool upgrade'.
# The following is the difference between the old and the new license text.
# Please update the LICENSE value if needed, and summarize the changes in
# the commit message via 'License-Update:' tag.
# (example: 'License-Update: copyright years updated.')
#
# The changes:
#
# --- MIT-LICENSE
# +++ MIT-LICENSE
# @@ -1,4 +1,4 @@
# -Copyright (c) 2018 Luminoso Technologies, Inc.
# +Copyright (c) 2012-2022 Elia Robyn Lake
#  
#  Permission is hereby granted, free of charge, to any person obtaining a
#  copy of this software and associated documentation files (the "Software"),
# 
#

SUMMARY = "A MutableSet that remembers its order, so that every entry has an index."
HOMEPAGE = "http://github.com/LuminosoInsight/ordered-set"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://MIT-LICENSE;md5=3bf5e1ad64c0d99032c3143361fa234e"

SRC_URI[sha256sum] = "694a8e44c87657c59292ede72891eb91d34131f6531463aab3009191c77364a8"

inherit pypi setuptools3 ptest

DEPENDS += "python3-pytest-runner-native"

SRC_URI += " \
	file://run-ptest \
"

RDEPENDS:${PN}-ptest += " \
	${PYTHON_PN}-pytest \
"

do_install_ptest() {
	cp -f ${S}/test.py ${D}${PTEST_PATH}/
}
