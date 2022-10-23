@file:JsExport @file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe

import cache.load
import cache.save
import koncurrent.later.catch
import live.MutableLive
import live.mutableLiveOf
import presenters.collections.*
import presenters.scopes.View
import viewmodel.BaseViewModel
import viewmodel.ScopeConfig
import kotlin.js.JsExport

abstract class CollectionsViewModel<T>(private val config: ScopeConfig<*>) : BaseViewModel(config) {

    abstract val paginator: PaginationManager<T>

    abstract val selector: SelectionManager<T>

    abstract val actions: ActionsManager<T>

    abstract val list: ScrollableList<T>

    abstract val table: Table<T>

    val view: MutableLive<View> = mutableLiveOf(View.ListView)

    private val cache by lazy { config.cache }

    companion object {
        private const val PREFERRED_VIEW = "preferred.view"

        private val DEFAULT_VIEW = View.ListView
    }

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
}