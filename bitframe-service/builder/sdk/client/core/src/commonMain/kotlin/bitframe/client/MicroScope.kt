@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION", "NON_EXPORTABLE_TYPE")

package bitframe.client

import viewmodel.ViewModel
import kotlin.js.JsExport
import kotlin.js.JsName

interface MicroScope<out I, out C, out S> {
    val config: UIScopeConfig<*>
    val viewModel: ViewModel<*, out S>
    val intents: I
}