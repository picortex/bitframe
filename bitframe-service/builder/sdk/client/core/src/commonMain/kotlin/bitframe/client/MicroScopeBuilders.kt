@file:Suppress("FunctionName")
@file:OptIn(ExperimentalTypeInference::class)

package bitframe.client

import kotlin.experimental.ExperimentalTypeInference

fun <I, S> MicroScope(
    @BuilderInference builder: MicroScopeBuilder<I, S>.() -> Unit
): Lazy<MicroScope<I, S>> = lazy {
    val scopeBuilder = MicroScopeBuilder<I, S>().apply(builder)
    MicroScopeImpl(scopeBuilder.viewModel, scopeBuilder.intents)
}

fun <I, S, C> FullMicroScope(
    @BuilderInference builder: MicroScopeWithConstantsBuilder<I, S, C>.() -> Unit
): Lazy<MicroScopeWithConstants<I, S, C>> = lazy {
    val scopeBuilder = MicroScopeWithConstantsBuilder<I, S, C>().apply(builder)
    MicroScopeWithConstantsImpl(
        viewModel = scopeBuilder.viewModel,
        intents = scopeBuilder.intents,
        constants = scopeBuilder.constants,
    )
}