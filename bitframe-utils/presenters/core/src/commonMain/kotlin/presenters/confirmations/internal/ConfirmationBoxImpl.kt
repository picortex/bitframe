package presenters.confirmations.internal

import presenters.actions.MutableSimpleAction
import presenters.confirmations.ConfirmationBox
import presenters.confirmations.ConfirmationState
import viewmodel.ScopeConfig
import viewmodel.ViewModel

class ConfirmationBoxImpl(
    override val heading: String,
    override val details: String,
    private val config: ScopeConfig<*>,
) : ViewModel<ConfirmationState>(config.of(ConfirmationState.Pending)), ConfirmationBox {

    override val cancelAction: MutableSimpleAction
        get() = TODO("Not yet implemented")

    override fun confirm() {
        TODO("Not yet implemented")
    }
}