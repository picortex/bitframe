package bitframe.internal

import bitframe.AppScopeConfig
import koncurrent.Executor
import kotlinx.serialization.StringFormat
import logging.Logger
import viewmodel.ScopeConfig
import viewmodel.StatefulViewModelConfig

internal class AppScopeConfigImpl<A>(
    override val api: A,
    override val executor: Executor,
    override val logger: Logger,
    override val codec: StringFormat
) : AppScopeConfig<A>, ScopeConfig<A> by ScopeConfig(api, executor, logger)