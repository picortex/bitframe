package bitframe.client

import kotlinx.coroutines.CoroutineScope
import logging.Logger

internal data class UIScopedViewModelConfigImpl(
    override val recoveryTime: Long = UIScopedViewModelConfig.DEFAULT_RECOVERY_TIME,
    override val transitionTime: Long = UIScopedViewModelConfig.DEFAULT_TRANSITION_TIME,
    override val logger: Logger = UIScopedViewModelConfig.DEFAULT_LOGGER,
    override val scopeBuilder: () -> CoroutineScope = UIScopedViewModelConfig.DEFAULT_SCOPE_BUILDER
) : UIScopedViewModelConfig