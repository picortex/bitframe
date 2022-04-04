package bitframe.client

import viewmodel.ViewModel

internal data class MicroScopeWithConstantsImpl<out I, out S, out C>(
    override val viewModel: ViewModel<*, out S>,
    override val intents: I,
    override val constants: C
) : MicroScopeWithConstants<I, S, C>