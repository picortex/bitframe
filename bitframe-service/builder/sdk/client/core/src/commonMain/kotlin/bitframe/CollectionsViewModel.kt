@file:JsExport @file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe

import cache.load
import cache.save
import koncurrent.later.catch
import koncurrent.later.then
import live.MutableLive
import live.mutableLiveOf
import presenters.collections.ActionsManager
import presenters.collections.PaginationManager
import presenters.collections.SelectionManager
import presenters.scopes.View
import viewmodel.BaseViewModel
import kotlin.js.JsExport

abstract class CollectionsViewModel<T>(private val config: AppScopeConfig<*>) : BaseViewModel(config) {

    abstract val paginator: PaginationManager<T>

    abstract val selector: SelectionManager<T>

    abstract val actions: ActionsManager<T>

    val view: MutableLive<View> = mutableLiveOf(View.ListView)

    private val cache by lazy { config.cache }

    companion object {
        private const val PREFERRED_VIEW = "preferred.view"

        private val DEFAULT_VIEW = View.ListView
    }

    fun switchToLatestSelectedView() {
        cache.load<View>(keyOf(PREFERRED_VIEW)).then {
            view.value = it
        }.catch {
            view.value = DEFAULT_VIEW
        }
    }

    fun switchToListView() = switchTo(View.ListView)

    fun switchToTableView() = switchTo(View.TableView)

    private fun switchTo(v: View) = cache.save(keyOf(PREFERRED_VIEW), v).then {
        view.value = it
    }
}