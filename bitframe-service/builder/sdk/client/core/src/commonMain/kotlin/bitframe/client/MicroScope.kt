@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package bitframe.client

import viewmodel.ViewModel
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface MicroScope<out I, out S> {
    val viewModel: ViewModel<*, out S>
    val intents: I
}