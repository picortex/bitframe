@file:Suppress("FunctionName")
@file:OptIn(ExperimentalTypeInference::class)

package bitframe.client

import viewmodel.ViewModel
import kotlin.experimental.ExperimentalTypeInference

fun <I, S> MicroScope(
    viewModel: ViewModel<*, S>,
    @BuilderInference builder: MicroScopeBuilder<I, S>.() -> Unit
): Lazy<MicroScope<I, S>> = lazy {
    val scopeBuilder = MicroScopeBuilder<I, S>(viewModel).apply(builder)
    object : MicroScope<I, S> {
        override val viewModel = scopeBuilder.viewModel
        override val intents = scopeBuilder.intents
    }
}

fun <I, S, C> FullMicroScope(
    viewModel: ViewModel<*, S>,
    @BuilderInference builder: MicroScopeWithConstantsBuilder<I, S, C>.() -> Unit
): Lazy<MicroScopeWithConstants<I, S, C>> = lazy {
    val scopeBuilder = MicroScopeWithConstantsBuilder<I, S, C>(viewModel).apply(builder)
    object : MicroScopeWithConstants<I, S, C> {
        override val viewModel = scopeBuilder.viewModel
        override val intents = scopeBuilder.intents
        override val constants = scopeBuilder.constants
    }
}