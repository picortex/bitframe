package bitframe.client

import viewmodel.ViewModel

open class MicroScopeBuilder<I, S, V : ViewModel<*, S>> {
    private var mViewModel: V? = null
    val viewModel: V get() = mViewModel ?: error("ViewModel has not been set yet")
    fun viewModel(vm: V) {
        mViewModel = vm
    }

    private var mIntents: I? = null
    val intents: I get() = mIntents ?: error("Intents have not been set yet")
    fun intents(wrapper: I) {
        mIntents = wrapper
    }

    open fun build(): MicroScope<I, S> = MicroScope(viewModel, intents)
}