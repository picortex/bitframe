package bitframe.client

import viewmodel.ViewModel

class MicroScopeWithConstantsBuilder<I, S, C, V : ViewModel<*, S>> : MicroScopeBuilder<I, S, V>() {
    private var mConstants: C? = null
    val constants: C get() = mConstants ?: error("Constants have not yet been set")

    fun constants(wrapper: C) {
        mConstants = wrapper
    }
}