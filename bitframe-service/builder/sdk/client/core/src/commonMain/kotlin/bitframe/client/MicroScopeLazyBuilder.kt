@file:Suppress("FunctionName")
@file:OptIn(ExperimentalTypeInference::class)

package bitframe.client

import viewmodel.ViewModel
import kotlin.experimental.ExperimentalTypeInference

fun <W, I, S, V : ViewModel<I, S>> MicroScope(
    @BuilderInference builder: MicroScopeBuilder<W, I, S, V>.() -> Unit
): Lazy<MicroScope<W, S>> = lazy { MicroScopeBuilder<W, I, S, V>().apply(builder).build() }