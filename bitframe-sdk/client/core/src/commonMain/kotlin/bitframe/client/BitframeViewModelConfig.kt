package bitframe.client

import bitframe.api.BitframeService
import kotlinx.coroutines.CoroutineScope
import logging.Logger
import viewmodel.ViewModelConfig
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface BitframeViewModelConfig : ViewModelConfig {
    val service: BitframeService
    val recoveryTime: Long
    val transitionTime: Long

    companion object {

        @JvmField
        val DEFAULT_LOGGER = ViewModelConfig.DEFAULT_LOGGER

        @JvmField
        val DEFAULT_SCOPE_BUILDER = ViewModelConfig.DEFAULT_SCOPE_BUILDER

        @JvmField
        val DEFAULT_RECOVERY_TIME = 3000L

        @JvmField
        val DEFAULT_TRANSITION_TIME = 3000L

        @JvmSynthetic
        operator fun invoke(
            service: BitframeService,
            recoveryTime: Long = DEFAULT_RECOVERY_TIME,
            transitionTime: Long = DEFAULT_TRANSITION_TIME,
            logger: Logger = DEFAULT_LOGGER,
            builder: () -> CoroutineScope = DEFAULT_SCOPE_BUILDER
        ): BitframeViewModelConfig = object : BitframeViewModelConfig {
            override val service: BitframeService = service
            override val recoveryTime: Long = recoveryTime
            override val transitionTime: Long = transitionTime
            override val logger: Logger = logger
            override val scopeBuilder: () -> CoroutineScope = builder
        }

        @JvmStatic
        @JvmOverloads
        fun create(
            service: BitframeService,
            recoveryTime: Long = DEFAULT_RECOVERY_TIME,
            transitionTime: Long = DEFAULT_TRANSITION_TIME,
            logger: Logger = DEFAULT_LOGGER,
            builder: () -> CoroutineScope = DEFAULT_SCOPE_BUILDER
        ): BitframeViewModelConfig = invoke(service, recoveryTime, transitionTime, logger, builder)
    }
}