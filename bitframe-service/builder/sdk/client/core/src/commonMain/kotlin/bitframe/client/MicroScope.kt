@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

import viewmodel.ViewModel
import kotlin.js.JsExport
import kotlin.js.JsName

open class MicroScope<out W, in I, S>(
    open val viewModel: ViewModel<I, S>,
    open val intents: W
)