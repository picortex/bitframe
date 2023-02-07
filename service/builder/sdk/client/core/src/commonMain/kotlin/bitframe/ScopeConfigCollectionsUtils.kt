package bitframe

import bitframe.internal.CollectionScopeConfigImpl
import presenters.collections.CollectionActionsBuilder
import presenters.collections.ColumnsBuilder
import viewmodel.ScopeConfig

fun <T, A> ScopeConfig<A>.collection(): CollectionScopeConfig<T, A> = CollectionScopeConfigImpl({}, {}, api, executor, logger, codec, cache, workManager)

fun <T, A> ScopeConfig<A>.configure(
    actions: CollectionActionsBuilder<T>.() -> Unit,
    columns: ColumnsBuilder<T>.() -> Unit
): CollectionScopeConfig<T, A> = CollectionScopeConfigImpl(actions, columns, api, executor, logger, codec, cache, workManager)

fun <T, A> ScopeConfig<A>.actions(
    actions: CollectionActionsBuilder<T>.() -> Unit,
): CollectionScopeConfig<T, A> = CollectionScopeConfigImpl(actions, {}, api, executor, logger, codec, cache, workManager)

fun <T, A> ScopeConfig<A>.columns(
    columns: ColumnsBuilder<T>.() -> Unit
): CollectionScopeConfig<T, A> = CollectionScopeConfigImpl({ }, columns, api, executor, logger, codec, cache, workManager)