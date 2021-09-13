package pimonitor.screens.api

import kotlinx.coroutines.runTest

fun <P : Screen> P.test(block: suspend P.() -> Unit) = runTest { block() }