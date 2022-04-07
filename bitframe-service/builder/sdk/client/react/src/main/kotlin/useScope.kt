@file:Suppress("NON_EXPORTABLE_TYPE")

import bitframe.client.*
import viewmodel.asState

@JsExport
fun <I, S> useScope(scope: MicroScope<I, S>): MicroScopeHook<I, S> = MicroScopeHookImpl(
    intents = scope.intents,
    state = scope.viewModel.asState()
)

@JsExport
@JsName("useScopeWithConstants")
fun <I, S, C> useScope(scope: MicroScopeWithConstants<I, S, C>): MicroScopeWithConstantsHook<I, S, C> = MicroScopeWithConstantsHookImpl(
    intents = scope.intents,
    state = scope.viewModel.asState(),
    constants = scope.constants
)