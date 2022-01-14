@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package bitframe.client

import bitframe.api.BitframeService
import viewmodel.ViewModel
import kotlin.js.JsExport
import kotlin.js.JsName

interface UIScope<in I, S> {
    val service: BitframeService
    val viewModel: ViewModel<I, S>
}