package bitframe.client

import viewmodel.ViewModel

class MiniScopeBuilder<W, I, S, C, V : ViewModel<I, S>> : MicroScopeBuilder<W, I, S, V>() {
    private var mConstants: C? = null
    val constants: C get() = mConstants ?: error("Constants have not yet been set")
    fun constants(wrapper: C) {
        mConstants = wrapper
    }

    override fun build(): MiniScope<W, S, C> = MiniScope(viewModel, intents, constants)
}