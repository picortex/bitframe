package bitframe.client

import viewmodel.ViewModel

class MicroScopeWithConstantsBuilder<I, S, C>(
    override val viewModel: ViewModel<*, S>
) : MicroScopeBuilder<I, S>(viewModel) {
    private var mConstants: C? = null
    val constants: C get() = mConstants ?: error("Constants have not yet been set")

    fun constants(wrapper: C) {
        mConstants = wrapper
    }
}