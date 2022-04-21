package pimonitor.client.utils.live

import live.MutableLive
import presenters.cases.CentralState
import presenters.cases.Emphasis.None
import presenters.cases.GenericState

fun <S> MutableLive<S>.update(updater: S.() -> S) {
    value = value.updater()
}

fun <C, D> MutableLive<CentralState<C, D>>.removeEmphasis() = update { copy(emphasis = None) }

fun <D> MutableLive<GenericState<D>>.exitDialog() {
    value = value.copy(dialog = null)
}