@file:Suppress("FunctionName")
@file:OptIn(ExperimentalTypeInference::class)

package bitframe.client

import viewmodel.ViewModel
import kotlin.experimental.ExperimentalTypeInference

fun <I, S, C, V : ViewModel<*, S>> MiniScope(
    @BuilderInference builder: MiniScopeBuilder<I, S, C, V>.() -> Unit
): Lazy<MiniScope<I, S, C>> = lazy { MiniScopeBuilder<I, S, C, V>().apply(builder).build() }