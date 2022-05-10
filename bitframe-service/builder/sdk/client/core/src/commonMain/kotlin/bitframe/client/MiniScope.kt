@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

import viewmodel.ViewModel
import kotlin.js.JsExport

class MiniScope<out I, S, out C>(
    override val viewModel: ViewModel<*, S>,
    override val intents: I,
    val constants: C
) : MicroScope<I, S>(viewModel, intents)