package pimonitor.pages

import test.asyncTest

fun <P : Page> P.test(block: suspend P.() -> Unit) = asyncTest { block() }