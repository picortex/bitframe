@file:JsExport @file:Suppress("WRONG_EXPORTED_DECLARATION")

package bitframe.client

import viewmodel.ViewModel
import kotlin.js.JsExport
import kotlin.js.JsName

interface UIScope<out S> {
    val config: UIScopeConfig<*>
    val viewModel: ViewModel<*, out S>
}