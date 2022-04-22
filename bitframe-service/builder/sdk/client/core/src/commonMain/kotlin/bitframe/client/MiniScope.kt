@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

import viewmodel.ViewModel
import kotlin.js.JsExport

class MiniScope<out W, in I, S, out C>(
    override val viewModel: ViewModel<I, S>,
    override val intents: W,
    val constants: C
) : MicroScope<W, I, S>(viewModel, intents)