package bitframe.client

import viewmodel.ViewModel

internal data class MicroScopeImpl<out I, out S>(
    override val viewModel: ViewModel<*, out S>,
    override val intents: I
) : MicroScope<I, S>