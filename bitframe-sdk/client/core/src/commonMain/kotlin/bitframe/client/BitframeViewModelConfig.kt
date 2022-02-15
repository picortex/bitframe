package bitframe.client

import kotlinx.coroutines.CoroutineScope
import logging.Logger
import viewmodel.ViewModelConfig
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface BitframeViewModelConfig : ViewModelConfig {
    val recoveryTime: Long
    val transitionTime: Long

    companion object {
        @JvmField
        val DEFAULT_RECOVERY_TIME = 3000L

        @JvmField
        val DEFAULT_TRANSITION_TIME = 3000L

        @JvmField
        val DEFAULT_LOGGER = ViewModelConfig.DEFAULT_LOGGER

        @JvmField
        val DEFAULT_SCOPE_BUILDER = ViewModelConfig.DEFAULT_SCOPE_BUILDER

        @JvmSynthetic
        operator fun invoke(
            recoveryTime: Long = DEFAULT_RECOVERY_TIME,
            transitionTime: Long = DEFAULT_TRANSITION_TIME,
            logger: Logger = DEFAULT_LOGGER,
            scopeBuilder: () -> CoroutineScope = DEFAULT_SCOPE_BUILDER
        ) = object : BitframeViewModelConfig {
            override val recoveryTime = recoveryTime
            override val transitionTime = transitionTime
            override val logger: Logger = logger
            override val scopeBuilder: () -> CoroutineScope = scopeBuilder
        }

        @JvmStatic
        @JvmOverloads
        fun create(
            recoveryTime: Long = DEFAULT_RECOVERY_TIME,
            transitionTime: Long = DEFAULT_TRANSITION_TIME,
            logger: Logger = DEFAULT_LOGGER,
            scopeBuilder: () -> CoroutineScope = DEFAULT_SCOPE_BUILDER
        ) = invoke(recoveryTime, transitionTime, logger, scopeBuilder)
    }
}