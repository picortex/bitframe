@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe

import live.watchAsState
import viewmodel.ViewModel

fun <V : ViewModel<S>, S> useScope(viewModel: V): Scope<V, S> = Scope(
    intents = viewModel,
    state = viewModel.ui.watchAsState()
)