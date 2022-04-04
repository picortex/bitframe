@file:Suppress("FunctionName")
@file:OptIn(ExperimentalTypeInference::class)

package bitframe.client

import viewmodel.ViewModel
import kotlin.experimental.ExperimentalTypeInference

fun <I, S, V : ViewModel<*, S>> MicroScope(
    viewModel: V,
    @BuilderInference builder: MicroScopeBuilder<I, S>.(V) -> Unit
): Lazy<MicroScope<I, S>> = lazy {
    val scopeBuilder = MicroScopeBuilder<I, S>(viewModel).apply { builder(viewModel) }
    object : MicroScope<I, S> {
        override val viewModel = scopeBuilder.viewModel
        override val intents = scopeBuilder.intents
    }
}

fun <I, S, C, V : ViewModel<*, S>> FullMicroScope(
    viewModel: V,
    @BuilderInference builder: MicroScopeWithConstantsBuilder<I, S, C>.(V) -> Unit
): Lazy<MicroScopeWithConstants<I, S, C>> = lazy {
    val scopeBuilder = MicroScopeWithConstantsBuilder<I, S, C>(viewModel).apply { builder(viewModel) }
    object : MicroScopeWithConstants<I, S, C> {
        override val viewModel = scopeBuilder.viewModel
        override val intents = scopeBuilder.intents
        override val constants = scopeBuilder.constants
    }
}