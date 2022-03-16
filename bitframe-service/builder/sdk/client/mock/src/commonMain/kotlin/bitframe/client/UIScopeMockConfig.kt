package bitframe.client

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import logging.Logger
import viewmodel.ViewModelConfig
import kotlin.jvm.JvmField
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

fun <S : Any> MockUIScopeConfig(
    service: S,
    recoveryTime: Long = 0L,
    transitionTime: Long = 0L,
    logger: Logger = UIScopeConfig.DEFAULT_LOGGER,
    builder: () -> CoroutineScope = { CoroutineScope(SupervisorJob() + Dispatchers.Default) }
) = UIScopeConfig(service, recoveryTime, transitionTime, logger, builder)