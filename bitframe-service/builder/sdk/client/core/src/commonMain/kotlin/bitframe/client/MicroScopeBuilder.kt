package bitframe.client

import viewmodel.ViewModel

open class MicroScopeBuilder<I, S> {
    private var mViewModel: ViewModel<*, S>? = null
    val viewModel: ViewModel<I, S> get() = error("ViewModel has not been set yet")
    fun viewModel(vm: ViewModel<*, S>) {
        mViewModel = vm
    }

    private var mIntents: I? = null
    val intents: I get() = mIntents ?: error("Intents have not been set yet")
    fun intents(wrapper: I) {
        mIntents = wrapper
    }
}