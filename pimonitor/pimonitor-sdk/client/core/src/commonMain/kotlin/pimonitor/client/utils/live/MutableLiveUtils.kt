package pimonitor.client.utils.live

import live.MutableLive
import presenters.cases.CentralState
import presenters.cases.Emphasis.None

fun <S> MutableLive<S>.update(updater: S.() -> S) {
    value = value.updater()
}

fun <E> MutableLive<CentralState<E>>.removeEmphasis() = update { copy(emphasis = None) }