@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package bitframe.client

import viewmodel.ViewModel
import kotlin.js.JsExport
import kotlin.js.JsName

interface UIScope<in I, S> {
    val config: UIScopeConfig<*>
    val viewModel: ViewModel<I, out S>

    @JsName("_ignore_post")
    fun post(intent: I) {
        viewModel.post(intent)
    }
}