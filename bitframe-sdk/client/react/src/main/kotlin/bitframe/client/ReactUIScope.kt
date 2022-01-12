@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package bitframe.client

import useViewModelState

interface ReactUIScope<in I, S> : UIScope<I, S> {
    val useStateFromViewModel: () -> S
        get() = {
            useViewModelState(viewModel)
        }
}