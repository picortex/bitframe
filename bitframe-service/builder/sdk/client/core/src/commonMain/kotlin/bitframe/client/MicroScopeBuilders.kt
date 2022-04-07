@file:Suppress("FunctionName")
@file:OptIn(ExperimentalTypeInference::class)

package bitframe.client

import kotlinx.coroutines.CoroutineScope
import viewmodel.ViewModel
import kotlin.experimental.ExperimentalTypeInference

fun <I, S, V : ViewModel<*, S>> MicroScope(
    @BuilderInference builder: MicroScopeBuilder<I, S, V>.() -> Unit
): Lazy<MicroScope<I, S>> = lazy {
    val scopeBuilder = MicroScopeBuilder<I, S, V>().apply(builder)
    MicroScopeImpl(scopeBuilder.viewModel, scopeBuilder.intents)
}

fun <I, S, C, V : ViewModel<*, S>> FullMicroScope(
    @BuilderInference builder: MicroScopeWithConstantsBuilder<I, S, C, V>.() -> Unit
): Lazy<MicroScopeWithConstants<I, S, C>> = lazy {
    val scopeBuilder = MicroScopeWithConstantsBuilder<I, S, C, V>().apply(builder)
    MicroScopeWithConstantsImpl(
        viewModel = scopeBuilder.viewModel,
        intents = scopeBuilder.intents,
        constants = scopeBuilder.constants,
    )
}