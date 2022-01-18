package pimonitor

import bitframe.client.BitframeViewModelConfig
import kotlinx.coroutines.CoroutineScope
import logging.Logger
import pimonitor.api.PiMonitorService
import viewmodel.ViewModelConfig
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface PiMonitorViewModelConfig : BitframeViewModelConfig {
    override val service: PiMonitorService

    companion object {

        @JvmField
        val DEFAULT_LOGGER = ViewModelConfig.DEFAULT_LOGGER

        @JvmField
        val DEFAULT_SCOPE_BUILDER = ViewModelConfig.DEFAULT_SCOPE_BUILDER

        @JvmField
        val DEFAULT_RECOVERY_TIME = BitframeViewModelConfig.DEFAULT_RECOVERY_TIME

        @JvmField
        val DEFAULT_TRANSITION_TIME = BitframeViewModelConfig.DEFAULT_TRANSITION_TIME

        @JvmSynthetic
        operator fun invoke(
            service: PiMonitorService,
            recoveryTime: Long = DEFAULT_RECOVERY_TIME,
            transitionTime: Long = DEFAULT_TRANSITION_TIME,
            logger: Logger = DEFAULT_LOGGER,
            builder: () -> CoroutineScope = DEFAULT_SCOPE_BUILDER
        ): PiMonitorViewModelConfig = object : PiMonitorViewModelConfig {
            override val service = service
            override val recoveryTime: Long = recoveryTime
            override val transitionTime: Long = transitionTime
            override val logger: Logger = logger
            override val scopeBuilder: () -> CoroutineScope = builder
        }

        @JvmStatic
        @JvmOverloads
        fun create(
            service: PiMonitorService,
            recoveryTime: Long = DEFAULT_RECOVERY_TIME,
            transitionTime: Long = DEFAULT_TRANSITION_TIME,
            logger: Logger = DEFAULT_LOGGER,
            builder: () -> CoroutineScope = DEFAULT_SCOPE_BUILDER
        ): PiMonitorViewModelConfig = invoke(service, recoveryTime, transitionTime, logger, builder)
    }
}