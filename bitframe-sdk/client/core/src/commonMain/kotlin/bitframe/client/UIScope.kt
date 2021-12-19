@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package bitframe.client

import viewmodel.ViewModel
import kotlin.js.JsExport
import kotlin.js.JsName

interface UIScope<in I, S> {
    val viewModel: ViewModel<I, S>

    companion object {
        @JsName("__invoke1__")
        operator fun <I, S> invoke(builder: () -> ViewModel<I, S>) = object : UIScope<I, S> {
            override val viewModel: ViewModel<I, S> by lazy(builder)
        }

        @JsName("__invoke2__")
        operator fun <I, S> invoke(viewModel: ViewModel<I, S>) = object : UIScope<I, S> {
            override val viewModel: ViewModel<I, S> = viewModel
        }
    }
}