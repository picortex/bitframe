package bitframe.client

class MicroScopeWithConstantsBuilder<I, S, C> : MicroScopeBuilder<I, S>() {
    private var mConstants: C? = null
    val constants: C get() = mConstants ?: error("Constants have not yet been set")

    fun constants(wrapper: C) {
        mConstants = wrapper
    }
}