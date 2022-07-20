@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.scopes

import live.MutableLive
import live.mutableLiveOf
import viewmodel.BaseViewModel
import viewmodel.ViewModelConfig
import kotlin.js.JsExport

abstract class MasterDetailViewModel<T>(config: ViewModelConfig<*>) : BaseViewModel(config) {

    val data: MutableLive<MasterState<T>> = mutableLiveOf(MasterState.Loading("Loading, please wait . . ."))

    val view: MutableLive<View> = mutableLiveOf(View.ListView)

    fun switchViewTo(v: View) {
        view.value = v
    }
}