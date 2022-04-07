@file:Suppress("NON_EXPORTABLE_TYPE")

import bitframe.client.*
import live.watchAsState
import viewmodel.asState

@JsExport
fun <I, S> useMicroScope(scope: MicroScope<I, S>): MicroScopeHook<I, S> = MicroScopeHook(
    intents = scope.intents,
    state = scope.viewModel.ui.watchAsState()
)

@JsExport
fun <I, S> useScope(scope: MicroScope<I, S>): MicroScopeHook<I, S> = useMicroScope(scope)

@JsExport
fun <I, S, C> useMiniScope(scope: MiniScope<I, S, C>): MiniScopeHook<I, S, C> = MiniScopeHook(
    intents = scope.intents,
    state = scope.viewModel.ui.watchAsState(),
    constants = scope.constants
)