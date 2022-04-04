@file:Suppress("NON_EXPORTABLE_TYPE")

import bitframe.client.MicroScope
import bitframe.client.MicroScopeHook
import bitframe.client.MicroScopeHookImpl
import viewmodel.asState

@JsExport
fun <I, S> useScope(scope: MicroScope<I, S>): MicroScopeHook<I, S> = MicroScopeHookImpl(
    intents = scope.intents,
    state = scope.viewModel.asState()
)