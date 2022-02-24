package bitframe.client

import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface BitframeAppScopeConfig<A : BitframeApi> {
    val api: A
    val viewModel: BitframeViewModelConfig

    // accessors
    val bus get() = api.config.bus
    val cache get() = api.config.cache

    operator fun invoke() = toConfig(api)

    operator fun <S : Any> invoke(
        builder: BitframeAppScopeConfig<A>.() -> S
    ) = toConfig(this.builder())

    fun <S : Any> toConfig(service: S) = UIScopeConfig(
        service = service,
        recoveryTime = viewModel.recoveryTime,
        transitionTime = viewModel.transitionTime,
        logger = viewModel.logger,
        builder = viewModel.scopeBuilder
    )

    companion object {
        @JvmSynthetic
        operator fun <A : BitframeApi> invoke(
            api: A,
            viewModel: BitframeViewModelConfig = BitframeViewModelConfig()
        ) = object : BitframeAppScopeConfig<A> {
            override val api = api
            override val viewModel = viewModel
        }

        @JvmStatic
        @JvmOverloads
        fun <A : BitframeApi> create(
            api: A,
            viewModel: BitframeViewModelConfig = BitframeViewModelConfig()
        ) = invoke(api, viewModel)
    }
}