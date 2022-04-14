@file:OptIn(ExperimentalTypeInference::class)

package bitframe.client

import presenters.intents.IndexIntent
import presenters.intents.IndexIntents
import viewmodel.ViewModel
import kotlin.experimental.ExperimentalTypeInference

fun <S, V : ViewModel<IndexIntent, S>> IndexMicroScope(
    @BuilderInference builder: MicroScopeBuilder<IndexIntents, IndexIntent, S, V>.() -> Unit
) = MicroScope {
    builder()
    intents(IndexIntents(viewModel))
}