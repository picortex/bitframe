@file:Suppress("NON_EXPORTABLE_TYPE")

import bitframe.client.*
import live.watchAsState
import viewmodel.asState

@JsExport
fun <IW, S> useMicroScope(scope: MicroScope<IW, S>): MicroScopeHook<IW, S> = MicroScopeHook(
    intents = scope.intents,
    state = scope.viewModel.ui.watchAsState()
)

@JsExport
fun <IW, S> useScope(scope: MicroScope<IW, S>): MicroScopeHook<IW, S> = useMicroScope(scope)

@JsExport
fun <IW, S, C> useMiniScope(scope: MiniScope<IW, S, C>): MiniScopeHook<IW, S, C> = MiniScopeHook(
    intents = scope.intents,
    state = scope.viewModel.ui.watchAsState(),
    constants = scope.constants
)