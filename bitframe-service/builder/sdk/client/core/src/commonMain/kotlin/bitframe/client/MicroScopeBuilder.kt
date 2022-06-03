package bitframe.client

import viewmodel.ViewModel

open class MicroScopeBuilder<W, I, S, V : ViewModel<I, S>> {
    private var mViewModel: V? = null
    val viewModel: V get() = mViewModel ?: error("ViewModel has not been set yet")
    fun viewModel(vm: V) {
        mViewModel = vm
    }

    private var mIntents: W? = null
    val intents: W get() = mIntents ?: error("Intents have not been set yet")
    fun intents(wrapper: W) {
        mIntents = wrapper
    }

    open fun build(): MicroScope<W, S> = MicroScope(viewModel, intents)
}