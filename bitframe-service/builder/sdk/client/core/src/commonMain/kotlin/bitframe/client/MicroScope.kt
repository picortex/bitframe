@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

import viewmodel.ViewModel
import kotlin.js.JsExport
import kotlin.js.JsName

open class MicroScope<out I, out S>(
    open val viewModel: ViewModel<*, out S>,
    open val intents: I
)