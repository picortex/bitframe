@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe

import cache.load
import cache.save
import koncurrent.Later
import koncurrent.later.finally
import kotlinx.serialization.KSerializer
import live.MutableLive
import live.mutableLiveOf
import presenters.collections.ActionsManager
import presenters.collections.ColumnsManager
import presenters.collections.Page
import presenters.collections.PaginationManager
import presenters.collections.ScrollableList
import presenters.collections.SelectionManager
import presenters.collections.Table
import presenters.collections.actionsOf
import presenters.collections.columnsOf
import presenters.collections.scrollableListOf
import presenters.collections.tableOf
import presenters.TextInputField
import presenters.collections.ColumnsBuilder
import presenters.scopes.View
import viewmodel.BaseViewModel
import viewmodel.ScopeConfig
import kotlin.js.JsExport
import kotlin.js.JsName

abstract class CollectionsViewModel<T>(private val config: CollectionScopeConfig<T, Any?>) : BaseViewModel(config) {
    @JsName("_ignore_constructor")
    constructor(config: ScopeConfig<Any?>) : this(config.collection())

    val view: MutableLive<View> = mutableLiveOf(DEFAULT_VIEW)

    val cache = config.cache

    protected abstract val loader: (no: Int, capacity: Int) -> Later<Collection<T>>

    protected abstract val serializer: KSerializer<T>

    protected inline fun columnsOf(noinline builder: ColumnsBuilder<T>.() -> Unit) = columnsOf(emptyList(), builder)

    val paginator: PaginationManager<T> by lazy {
        PaginationManager { no, capacity ->
            loader(no, capacity).then { Page(it, capacity, no) }
        }
    }

    val selector: SelectionManager<T> by lazy { SelectionManager(paginator) }

    open val actions: ActionsManager<T> by lazy { actionsOf(selector, config.actions) }

    open val columns: ColumnsManager<T> by lazy { columnsOf(emptyList(), config.columns) }

    val list: ScrollableList<T> by lazy { scrollableListOf(paginator, selector, actions) }

    val table: Table<T> by lazy { tableOf(paginator, selector, actions, columns) }

    private val preferredView = "${this::class.simpleName?.replace("ViewModel", "")}.$PREFERRED_VIEW"

    fun initialize() {
        switchToLatestSelectedView()
        paginator.loadFirstPage()
    }

    open fun deInitialize(clearPages: Boolean = false) {
        if (clearPages) paginator.clearPages()
    }

    fun switchToLatestSelectedView() = cache.load<View>(preferredView).finally {
        view.value = it.data ?: DEFAULT_VIEW
    }

    fun switchToListView() = switchTo(View.ListView)

    fun switchToTableView() = switchTo(View.TableView)

    private fun switchTo(v: View) = cache.save(preferredView, v).then {
        view.value = it
    }

    val searchBox = TextInputField(name = "search-box")

    fun search(): Later<Any> {
        paginator.clearPages()
        return paginator.loadFirstPage()
    }

    fun unselect(item: T? = null): Later<Unit?> = cache.remove(CacheKeys.SELECTED_ITEM)

    fun select(item: T): Later<T> {
        selector.select(item)
        return cache.save(CacheKeys.SELECTED_ITEM, item, serializer)
    }

    private companion object {
        const val PREFERRED_VIEW = "preferred.view"

        val DEFAULT_VIEW = View.ListView
    }
}