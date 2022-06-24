@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe

import viewmodel.ViewModel
import viewmodel.asState

fun <V : ViewModel<S>, S> useScope(viewModel: V): Scope<V, S> = Scope(
    intents = viewModel,
    state = viewModel.asState()
)