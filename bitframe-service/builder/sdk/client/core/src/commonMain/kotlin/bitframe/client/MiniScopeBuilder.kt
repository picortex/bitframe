package bitframe.client

import viewmodel.ViewModel

class MiniScopeBuilder<I, S, C, V : ViewModel<*, S>> : MicroScopeBuilder<I, S, V>() {
    private var mConstants: C? = null
    val constants: C get() = mConstants ?: error("Constants have not yet been set")
    fun constants(wrapper: C) {
        mConstants = wrapper
    }

    override fun build(): MiniScope<I, S, C> = MiniScope(viewModel, intents, constants)
}