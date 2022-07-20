package bitframe.internal

import bitframe.AppScopeConfig
import koncurrent.Executor
import kotlinx.serialization.StringFormat
import logging.Logger
import viewmodel.StatefulViewModelConfig

internal class AppScopeConfigImpl<A>(
    override val api: A,
    override val executor: Executor,
    override val logger: Logger,
    override val codec: StringFormat
) : AppScopeConfig<A> {

    // TODO: Provide a StatefulViewModelConfig constructor from viewmodel-core
    internal class StatefulViewModelConfigImpl<out A, out S>(
        override val api: A,
        override val state: S,
        override val executor: Executor,
        override val codec: StringFormat,
        override val logger: Logger
    ) : StatefulViewModelConfig<A, S> {
        override fun <R> map(transformer: (A) -> R) = StatefulViewModelConfigImpl(
            api = transformer(api), state, executor, codec, logger
        )

        override fun <T> of(state: T) = StatefulViewModelConfigImpl(
            api, state, executor, codec, logger
        )
    }

    override fun <S> of(state: S): StatefulViewModelConfig<A, S> = StatefulViewModelConfigImpl(api, state, executor, codec, logger)
    override fun <R> map(transformer: (A) -> R) = AppScopeConfigImpl(transformer(api), executor, logger, codec)
}