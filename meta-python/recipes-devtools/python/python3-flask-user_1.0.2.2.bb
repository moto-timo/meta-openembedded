# FIXME: the LIC_FILES_CHKSUM values have been updated by 'devtool upgrade'.
# The following is the difference between the old and the new license text.
# Please update the LICENSE value if needed, and summarize the changes in
# the commit message via 'License-Update:' tag.
# (example: 'License-Update: copyright years updated.')
#
# The changes:
#
# --- LICENSE.txt
# +++ LICENSE.txt
# @@ -1,22 +1,22 @@
# -Copyright (c) 2013, Ling Thio and contributors.
# +Copyright (c) 2013 Ling Thio
#  
# -Redistribution and use in source and binary forms, with or without
# -modification, are permitted provided that the following conditions are met:
# +Permission is hereby granted, free of charge, to any person
# +obtaining a copy of this software and associated documentation
# +files (the "Software"), to deal in the Software without
# +restriction, including without limitation the rights to use,
# +copy, modify, merge, publish, distribute, sublicense, and/or sell
# +copies of the Software, and to permit persons to whom the
# +Software is furnished to do so, subject to the following
# +conditions:
#  
# -1. Redistributions of source code must retain the above copyright notice, this
# -   list of conditions and the following disclaimer.
# +The above copyright notice and this permission notice shall be
# +included in all copies or substantial portions of the Software.
#  
# -2. Redistributions in binary form must reproduce the above copyright notice,
# -   this list of conditions and the following disclaimer in the documentation
# -   and/or other materials provided with the distribution.
# -
# -THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
# -ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
# -WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
# -DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
# -ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
# -(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
# -LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
# -ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
# -(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
# -SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
# +THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
# +EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
# +OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
# +NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
# +HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
# +WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
# +FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
# +OTHER DEALINGS IN THE SOFTWARE.
#

SUMMARY = "Customizable user account management for Flask"
DESCRIPTION = "Customizable User Account Management for Flask; Register \
Confirm email, Login, Change username, Change password, Forgot Password \
and more."
HOMEPAGE = " https://github.com/lingthio/Flask-User"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=5aabce758ead32427c145173bc555403"

SRC_URI[sha256sum] = "c9785febc2fc7ca15c69cfff1c8dabc2788f25043b54a33f73a6c2a9daf7fbe4"

PYPI_PACKAGE = "Flask-User"

inherit pypi setuptools3

RDEPENDS:${PN} = "${PYTHON_PN}-flask \
    ${PYTHON_PN}-flask-login \
    ${PYTHON_PN}-flask-mail \
    ${PYTHON_PN}-babel"
