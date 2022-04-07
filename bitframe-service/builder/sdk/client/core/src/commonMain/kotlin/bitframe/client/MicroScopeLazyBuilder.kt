@file:Suppress("FunctionName")
@file:OptIn(ExperimentalTypeInference::class)

package bitframe.client

import viewmodel.ViewModel
import kotlin.experimental.ExperimentalTypeInference

fun <I, S, V : ViewModel<*, S>> MicroScope(
    @BuilderInference builder: MicroScopeBuilder<I, S, V>.() -> Unit
): Lazy<MicroScope<I, S>> = lazy { MicroScopeBuilder<I, S, V>().apply(builder).build() }