package bitframe.screens.api

import kotlinx.coroutines.test.runTest

fun <P : Screen> P.test(block: suspend P.() -> Unit) = runTest { block() }