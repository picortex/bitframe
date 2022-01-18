package bitframe

import kotlinx.coroutines.test.runTest

fun <A : BrowserApplication> A.test(block: suspend A.() -> Unit) = runTest { block() }