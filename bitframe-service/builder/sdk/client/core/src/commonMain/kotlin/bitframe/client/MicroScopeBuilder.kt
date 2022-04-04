package bitframe.client

import viewmodel.ViewModel

open class MicroScopeBuilder<I, S>(open val viewModel: ViewModel<*, S>) {
    private var mIntents: I? = null
    val intents: I get() = mIntents ?: error("Intents have not been set yet")
    fun intents(wrapper: I) {
        mIntents = wrapper
    }
}