@file:Suppress("NON_EXPORTABLE_TYPE", "EXPERIMENTAL_API_USAGE")

import live.Live
import viewmodel.ViewModel

@JsExport
fun <S> useLive(state: Live<S>): S = live.useLive(state)

@JsExport
fun <S> useViewModelState(vm: ViewModel<*, S>): S = live.useLive(vm.ui)