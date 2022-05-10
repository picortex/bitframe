@file:Suppress("FunctionName")
@file:OptIn(ExperimentalTypeInference::class)

package bitframe.client

import viewmodel.ViewModel
import kotlin.experimental.ExperimentalTypeInference

fun <W, I, S, C, V : ViewModel<I, S>> MiniScope(
    @BuilderInference builder: MiniScopeBuilder<W, I, S, C, V>.() -> Unit
): Lazy<MiniScope<W, S, C>> = lazy { MiniScopeBuilder<W, I, S, C, V>().apply(builder).build() }