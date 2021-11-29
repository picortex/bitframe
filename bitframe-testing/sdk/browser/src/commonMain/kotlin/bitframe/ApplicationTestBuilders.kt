package bitframe

import kotlinx.coroutines.runTest

fun <A : BrowserApplication> A.test(block: suspend A.() -> Unit) = runTest { block() }