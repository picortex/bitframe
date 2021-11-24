package bitframe

import kotlinx.coroutines.runTest

fun <A : Application> A.test(block: suspend A.() -> Unit) = runTest { block() }