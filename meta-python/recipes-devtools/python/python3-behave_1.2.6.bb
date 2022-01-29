SUMMARY = "A behavior-driven development framework, Python style"
HOMEPAGE = "https://github.com/behave/behave"
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d950439e8ea6ed233e4288f5e1a49c06"

PV .= "+git${SRCREV}"
SRCREV = "c0f3faf47ff05f549ec049599eb2f24069b0e51e"
SRC_URI += "file://0001-FIXES-725-ScenarioOutlineBuilder-was-not-copying-des.patch \
           file://0002-UPDATE-FIXED-725.patch \
           file://0003-ADD-TEST-to-verify-that-issue-725-is-fixed.patch \
           file://0004-FIX-SummaryReporter-counts-computation-when-Rules-ar.patch \
           file://0005-SummaryReporter.print_summary-Simplify-if-Rules-are-.patch \
           file://0006-Formatter-Add-basic-support-output-for-Rules.patch \
           file://0007-BUMP-VERSION-1.2.7.dev1-was-1.2.7.dev0.patch \
           file://0008-Correct-examples-and-docstring.patch \
           file://0009-FIX-feature.run_items-processing-with-Rule-s.patch \
           file://0010-docs-Disable-sphinx-intl-support-for-READTHEDOCS.patch \
           file://0011-Cleanup-Dependent-package-versions-in-requirements.patch \
           file://0012-docs-conf.py-tweaks.patch \
           file://0013-FIX-Misspelled-after_rule-hook-was-after_after.patch \
           file://0014-Add-hints-on-Gherkin-v6-grammar-issues.patch \
           file://0015-README-ReST-tweaks.patch \
           file://0016-Example-using-Gherkin-v6-grammar.patch \
           file://0017-PREPARE-Python-3.8-support.patch \
           file://0018-py3.8-Try-to-fix-logging.Formatter-validate-problem.patch \
           file://0019-travis.ci-Temporarily-move-py38-dev-to-front-build-f.patch \
           file://0020-travis.ci-Tweaks-for-faster-builds-temporarily.patch \
           file://0021-FIX-py3.8-logging.Formatter.validate-problem.patch \
           file://0022-PREPARE-FOR-Python-3.8-asyncio.coroutine-is-deprecat.patch \
           file://0023-UPDATE-Add-755-info.patch \
           file://0024-FIX-WARNING-Related-to-docstring-example-and-weird-b.patch \
           file://0025-FIX-invalid-escape-sequence-warnings-w-regex-pattern.patch \
           file://0026-DEPRECATING-CLEANUP-Move-deprecated-tag-matcher-clas.patch \
           file://0027-Comment-tweaks.patch \
           file://0028-FIX-warnings-related-to-invalid-escapes-in-regex.patch \
           file://0029-Steps-catalog-should-not-break-configured-rerun-sett.patch \
           file://0030-Add-feature-w-rules-and-failing-scenarios-enable-via.patch \
           file://0031-examples-gherkin_v6-Tweak-ScenarioOutline-Examples-t.patch \
           file://0032-Add-info-on-merged-pull-588.patch \
           file://0033-Tweak-tests-required-by-pytest-5.0.patch \
           file://0034-CLEANUP-Use-pytest-instead-of-nose-to-remove-nose.im.patch \
           file://0035-REMOVE-DEPENDENCY-nose-to-avoid-nose.importer-warnin.patch \
           file://0036-FIX-Remove-test-from-pytest-run.patch \
           file://0037-Select-by-location-Add-support-for-Scenario-containe.patch \
           file://0038-docs-Add-description-for-Select-by-location-for-Scen.patch \
           file://0039-tests-Fix-warnings-for-python3.patch \
           file://0040-Use-cucumber-tag-expressions-1.1.2-to-fix-warnings.patch \
           file://0041-MENTION-ENHANCEMENT-Support-emojis-in-feature-files-.patch \
           file://0042-FIX-Invalid-escape-char-in-regex-w-python3.patch \
           file://0043-Example-related-to-question-in-756.patch \
           file://0044-FIX-python3.8-regressions-on-CI-server.patch \
           file://0045-UPDATE-Mark-issue-755-as-fixed.patch \
           file://0046-UPDATE-Cucumber-gherkin-languages.json.patch \
           file://0047-gherkin-Adding-Rule-keyword-translation-in-portugues.patch \
           file://0048-Tweaks-to-update-generate-from-gherkin-languages.jso.patch \
           file://0049-EXAMPLE-Tweak-naming-to-fixture.behave.no_background.patch \
           file://0050-EXAMPLE-Cleanup-Gherkin-v6-README.patch \
           file://0051-Improve-support-for-feature.background-inheritance-f.patch \
           file://0052-Add-support-for-runtime-constraints.patch \
           file://0053-Use-runtime-constraints.patch \
           file://0054-CLEANUP-Remove-deprecated-parts.patch \
           file://0055-CLEANUP-Remove-deprecated-parts.patch \
           file://0056-more.features-Perform-more-Gherkin-v6-checks-and-run.patch \
           file://0057-UTIL-Correct-URL-and-python-module-old-was-broken-no.patch \
           file://0058-UTIL-Formatting-tweaks.patch \
           file://0059-Fixed-a-bug-where-use_fixture_by_tag-didn-t-return-t.patch \
           file://0060-Added-issue-unit-test.patch \
           file://0061-Merge-pull-request-767-with-minor-tweaks.patch \
           file://0062-CHECK-Issue-766-PrettyFormatter-UnicodeError.patch \
           file://0063-FIX-issue-772-ScenarioOutline.Examples-without-table.patch \
           file://0064-FIX-issue-772-ScenarioOutline.Examples-without-table.patch \
           file://0065-Nibble-TravisCI-to-wake-up.patch \
           file://0066-Tweak-pytest-version-selection.patch \
           file://0067-Tweak-pytest-configuration-to-silence-JUnit-XML-dial.patch \
           file://0068-Add-basic-feature-test-for-wildcard-pattern-matching.patch \
           file://0069-UPDATE-dependencies-path.py-path-pytest.patch \
           file://0070-pytest-Disable-DeprecatedWarning-from-distutils-pack.patch \
           file://0071-CLEANUP-Add-ContextMode-enum-related-to-797.patch \
           file://0072-Cleanup-comments.patch \
           file://0073-FIX-sphinx-build-problem-async_steps3x.py.patch \
           file://0074-docs-Rename-page-parse_expressions-was-parse_builtin.patch \
           file://0075-docs-parse_expression-add-links-to-parse_type-module.patch \
           file://0076-BUMP-VERSION-1.2.7.dev2-was-1.2.7.dev1.patch \
           file://0077-Gherkin-parser-Cleanups-related-to-question-in-800-P.patch \
           file://0078-Clarify-select-by-name-uses-regex-pattern-related-to.patch \
           file://0079-FIX-Cross-reference-problem-copy-paste-in-Rule-class.patch \
           file://0080-DOCS-Update-API-description-for-Runner-Operation.patch \
           file://0081-FIX-DOCS-Runner-operations-typo.patch \
           file://0082-issue-740-Enhancement-Context.add_cleanup-with-layer.patch \
           file://0083-UPDATE-parse-1.18.0-parse-versions-1.16.0-.-1.17.x-h.patch \
           file://0084-RELATED-TO-Duplicated-steps-AmbiguousStepErrors.patch \
           file://0085-Add-renovate.json.patch \
           file://0086-PRPEPARE-RENOVATE-With-adaptions.patch \
           file://0087-Pin-dependencies.patch \
           file://0088-renovate-Extend-pip-requirements-file-list.patch \
           file://0089-PIN-REQUIREMENTS-Extend-to-all-places.patch \
           file://0090-tasks-Add-invoke_cleanup-replaces-_tasklet_cleanup-r.patch \
           file://0091-Docs-change-code-blocks-from-bash-to-console.patch \
           file://0092-Fix-typo-in-tutorial.patch \
           file://0093-py.requirements-Use-PyHamcrest-2.0-for-python2.7.patch \
           file://0094-UPDATE-PR-877-was-merged.patch \
           file://0095-capitalizing-steps.patch \
           file://0096-FIX-invoke-task-develop.update-gherkin-that-aborted-.patch \
           file://0097-Test-against-PowerPC-CPU-support-Travis-867.patch \
           file://0098-Add-Context.attach-docs-and-test.patch \
           file://0099-Add-Contributing-chapter.patch \
           file://0100-Adapt-Tox-target-for-building-the-docs.patch \
           file://0101-Mention-HTML-formatter-in-documentation.patch \
           file://0102-Use-console-highlighting-for-pip-install-docs.patch \
           file://0103-docs-fix-simple-typo-tuorial-tutorial.patch \
           file://0104-Add-support-for-python3.9-by-using-active-tags.patch \
           file://0105-PREFER-python3-from-now-on.patch \
           file://0106-REMOVE-invoke-scripts.patch \
           file://0107-FIX-Deprecated-warnings-for-Python-3.x.patch \
           file://0108-FIX-Python2-problems-in-virtualenvs-w-behave4cmd0-st.patch \
           file://0109-FIX-Active-tag-logic.patch \
           file://0110-FIX-Tests-w-more.features.patch \
           file://0111-FIX-Some-regressions-with-Python-3.9.patch \
           file://0112-docs-Update-new-and-noteworthy.patch \
           file://0113-Add-diagnostic-helper-function-to-print-the-current-.patch \
           file://0114-UPDATE-i18n-gherkin-languages.json-from-cucumber-rep.patch \
           file://0115-UPDATE-CHANGES-Related-to-PR-895-and-827.patch \
           file://0116-FIX-CI-TRAVIS-For-python-2.7-builds-mock-4.0-only-fo.patch \
           file://0117-Adds-the-ability-to-use-a-custom-runner-in-the-behav.patch \
           file://0118-Allow-forcing-color-with-color-always.patch \
           file://0119-Allow-color-with-no-value-followed-by-posarg.patch \
           file://0120-Add-BEHAVE_COLOR-env-var.patch \
           file://0121-fix-malformed-table-rows-warning.patch \
           file://0122-FIX-955-setup-Remove-attribute-use_2to3.patch \
           file://0123-Add-info-for-fixed-issue-955.patch \
           "

S = "${WORKDIR}/git"

inherit setuptools3

RDEPENDS:${PN} += " \
    ${PYTHON_PN}-parse-type \
    ${PYTHON_PN}-setuptools \
    ${PYTHON_PN}-six \
    "

PV = "6"
