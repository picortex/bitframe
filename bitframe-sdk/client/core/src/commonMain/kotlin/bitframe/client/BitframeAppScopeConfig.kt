package bitframe.client

import kotlinx.coroutines.CoroutineScope
import logging.Logger
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface BitframeAppScopeConfig {
    val api: BitframeApi
    val viewModel: BitframeViewModelConfig

    // accessors
    val bus get() = api.config.bus
    val cache get() = api.config.cache

    fun <S : Any> toConfig(service: S) = UIScopeConfig(
        service = service,
        recoveryTime = viewModel.recoveryTime,
        transitionTime = viewModel.transitionTime,
        logger = viewModel.logger,
        builder = viewModel.scopeBuilder
    )

    companion object {
        @JvmSynthetic
        operator fun invoke(
            api: BitframeApi,
            viewModel: BitframeViewModelConfig = BitframeViewModelConfig()
        ) = object : BitframeAppScopeConfig {
            override val api = api
            override val viewModel = viewModel
        }

        @JvmStatic
        @JvmOverloads
        fun create(
            api: BitframeApi,
            viewModel: BitframeViewModelConfig = BitframeViewModelConfig()
        ) = invoke(api, viewModel)
    }
}