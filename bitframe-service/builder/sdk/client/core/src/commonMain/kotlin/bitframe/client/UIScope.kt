@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

import viewmodel.ViewModel
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface UIScope<out S> {
    val config: UIScopeConfig<*>
    val viewModel: ViewModel<*, out S>
}