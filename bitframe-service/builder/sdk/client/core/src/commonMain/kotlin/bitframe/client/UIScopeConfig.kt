package bitframe.client

import kotlinx.coroutines.CoroutineScope
import logging.Logger
import viewmodel.ViewModelConfig
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface UIScopeConfig<out S : Any> {
    val service: S
    val viewModel: UIScopedViewModelConfig

    companion object {

        @JvmField
        val DEFAULT_LOGGER = ViewModelConfig.DEFAULT_LOGGER

        @JvmField
        val DEFAULT_SCOPE_BUILDER = ViewModelConfig.DEFAULT_SCOPE_BUILDER

        @JvmField
        val DEFAULT_RECOVERY_TIME = UIScopedViewModelConfig.DEFAULT_RECOVERY_TIME

        @JvmField
        val DEFAULT_TRANSITION_TIME = UIScopedViewModelConfig.DEFAULT_TRANSITION_TIME

        @JvmSynthetic
        operator fun <S : Any> invoke(
            service: S,
            recoveryTime: Long = DEFAULT_RECOVERY_TIME,
            transitionTime: Long = DEFAULT_TRANSITION_TIME,
            logger: Logger = DEFAULT_LOGGER,
            builder: () -> CoroutineScope = DEFAULT_SCOPE_BUILDER
        ) = object : UIScopeConfig<S> {
            override val service: S = service
            override val viewModel = UIScopedViewModelConfig(recoveryTime, transitionTime, logger, builder)
        }

        @JvmStatic
        @JvmOverloads
        fun <S : Any> create(
            service: S,
            recoveryTime: Long = DEFAULT_RECOVERY_TIME,
            transitionTime: Long = DEFAULT_TRANSITION_TIME,
            logger: Logger = DEFAULT_LOGGER,
            builder: () -> CoroutineScope = DEFAULT_SCOPE_BUILDER
        ) = invoke(service, recoveryTime, transitionTime, logger, builder)
    }
}