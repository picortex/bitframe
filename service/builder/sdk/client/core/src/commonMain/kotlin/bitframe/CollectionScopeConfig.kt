package bitframe

import presenters.collections.CollectionActionsBuilder
import presenters.collections.ColumnsBuilder
import viewmodel.ScopeConfig

interface CollectionScopeConfig<T, out A> : ScopeConfig<A> {
    val actions: CollectionActionsBuilder<T>.() -> Unit
    val columns: ColumnsBuilder<T>.() -> Unit
}