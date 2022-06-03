package pimonitor.client.utils.live

import live.MutableLive
import presenters.cases.CentralState
import presenters.cases.GenericState
import presenters.cases.copy
import presenters.cases.withoutEmphasis

fun <S> MutableLive<S>.update(updater: S.() -> S) {
    value = value.updater()
}

fun <C, D> MutableLive<CentralState<C, D>>.removeEmphasis() = update { withoutEmphasis() }

fun <D> MutableLive<GenericState<D>>.exitDialog() {
    value = value.copy(dialog = null)
}