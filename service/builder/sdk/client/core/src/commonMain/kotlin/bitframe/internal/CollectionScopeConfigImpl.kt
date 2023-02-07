package bitframe.internal

import bitframe.CollectionScopeConfig
import cache.Cache
import koncurrent.Executor
import kotlinx.serialization.StringFormat
import krest.WorkManager
import logging.Logger
import presenters.collections.CollectionActionsBuilder
import presenters.collections.ColumnsBuilder
import viewmodel.ViewModelConfig

class CollectionScopeConfigImpl<T, out A>(
    override val actions: CollectionActionsBuilder<T>.() -> Unit,
    override val columns: ColumnsBuilder<T>.() -> Unit,
    override val api: A,
    override val executor: Executor,
    override val logger: Logger,
    override val codec: StringFormat,
    override val cache: Cache,
    override val workManager: WorkManager
) : CollectionScopeConfig<T, A> {
    override fun <R> map(transformer: (A) -> R) = CollectionScopeConfigImpl(
        actions, columns, transformer(api), executor, logger, codec, cache, workManager
    )

    override fun <S> of(state: S) = ViewModelConfig(executor, logger, cache, codec, workManager).of(state)

//    override fun actionsOf(actions: ActionsManager<T>.() -> Unit) = CollectionScopeConfigImpl(
//        actions, columns, api, executor, logger, codec, cache, workManager
//    )
//
//    override fun columnsOf(columns: ColumnsManager<T>.() -> Unit) = CollectionScopeConfigImpl(
//        actions, columns, api, executor, logger, codec, cache, workManager
//    )
//
//    override fun configure(actions: ActionsManager<T>.() -> Unit, columns: ColumnsManager<T>.() -> Unit) = CollectionScopeConfigImpl(
//        actions, columns, api, executor, logger, codec, cache, workManager
//    )
}