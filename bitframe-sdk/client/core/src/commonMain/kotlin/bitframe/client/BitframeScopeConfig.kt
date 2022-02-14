@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package bitframe.client

import kotlinx.coroutines.CoroutineScope
import logging.Logger
import viewmodel.ViewModelConfig
import kotlin.js.JsExport
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

interface BitframeScopeConfig : ViewModelConfig {
    val api: BitframeApi
    val recoveryTime: Long
    val transitionTime: Long

    // accessors
    val bus get() = api.config.bus
    val cache get() = api.config.cache

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
            api: BitframeApi,
            recoveryTime: Long = DEFAULT_RECOVERY_TIME,
            transitionTime: Long = DEFAULT_TRANSITION_TIME,
            logger: Logger = DEFAULT_LOGGER,
            builder: () -> CoroutineScope = DEFAULT_SCOPE_BUILDER
        ): BitframeScopeConfig = object : BitframeScopeConfig {
            override val api: BitframeApi = api
            override val recoveryTime: Long = recoveryTime
            override val transitionTime: Long = transitionTime
            override val logger: Logger = logger
            override val scopeBuilder: () -> CoroutineScope = builder
        }

        @JvmStatic
        @JvmOverloads
        fun create(
            api: BitframeApi,
            recoveryTime: Long = DEFAULT_RECOVERY_TIME,
            transitionTime: Long = DEFAULT_TRANSITION_TIME,
            logger: Logger = DEFAULT_LOGGER,
            builder: () -> CoroutineScope = DEFAULT_SCOPE_BUILDER
        ): BitframeScopeConfig = invoke(api, recoveryTime, transitionTime, logger, builder)
    }
}