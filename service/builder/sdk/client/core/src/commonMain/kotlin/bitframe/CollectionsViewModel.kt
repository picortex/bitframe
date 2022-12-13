@file:JsExport @file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe

import cache.load
import cache.save
import koncurrent.Later
import koncurrent.later.catch
import kotlinx.serialization.KSerializer
import live.MutableLive
import live.mutableLiveOf
import presenters.collections.*
import presenters.fields.TextInputField
import presenters.scopes.View
import viewmodel.BaseViewModel
import viewmodel.ScopeConfig
import kotlin.js.JsExport

abstract class CollectionsViewModel<T>(private val config: ScopeConfig<*>) : BaseViewModel(config) {

    val view: MutableLive<View> = mutableLiveOf(View.ListView)

    val cache = config.cache

    abstract val paginator: PaginationManager<T>

    abstract val selector: SelectionManager<T>

    abstract val actions: ActionsManager<T>

    abstract val list: ScrollableList<T>

    abstract val table: Table<T>

    abstract val serializer: KSerializer<T>

    private val preferedView get() = "${this::class.simpleName?.replace("ViewModel", "")}.$PREFERRED_VIEW"

    fun switchToLatestSelectedView() {
        cache.load<View>(preferedView).then {
            view.value = it
        }.catch {
            view.value = DEFAULT_VIEW
        }
    }

    fun switchToListView() = switchTo(View.ListView)

    fun switchToTableView() = switchTo(View.TableView)

    private fun switchTo(v: View) = cache.save(preferedView, v).then {
        view.value = it
    }

    val searchBox = TextInputField(name = "search-box")

    fun search(): Later<Any> {
        paginator.wipeMemory()
        return paginator.loadFirstPage()
    }

    fun select(item: T): Later<T> {
        selector.select(item)
        return cache.save(CacheKeys.SELECTED_ITEM, item, serializer)
    }

    private companion object {
        const val PREFERRED_VIEW = "preferred.view"

        val DEFAULT_VIEW = View.ListView
    }
}