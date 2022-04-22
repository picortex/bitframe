package bitframe.client

import kotlinx.coroutines.CoroutineScope
import logging.Logger

internal data class UIScopeConfigImpl<S : Any>(
    override val service: S,
    override val viewModel: UIScopedViewModelConfig
) : UIScopeConfig<S> {
    constructor(
        service: S,
        recoveryTime: Long = UIScopeConfig.DEFAULT_RECOVERY_TIME,
        transitionTime: Long = UIScopeConfig.DEFAULT_TRANSITION_TIME,
        logger: Logger = UIScopeConfig.DEFAULT_LOGGER,
        builder: () -> CoroutineScope = UIScopeConfig.DEFAULT_SCOPE_BUILDER
    ) : this(service, UIScopedViewModelConfigImpl(recoveryTime, transitionTime, logger, builder))
}