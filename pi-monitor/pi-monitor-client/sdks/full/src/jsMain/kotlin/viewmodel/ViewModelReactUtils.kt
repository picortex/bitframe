package viewmodel

import useViewModelState
import kotlin.reflect.KProperty

operator fun <S> ViewModel<*, S>.getValue(thisRef: Nothing?, property: KProperty<*>): S {
    return useViewModelState(this)
}