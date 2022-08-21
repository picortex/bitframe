package presenters.confirmations.internal

import koncurrent.Later
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

    override val cancelAction = MutableSimpleAction(
        name = "Cancel",
        handler = { logger.warn("Cancel hasn't been handled yet") }
    )

    override fun confirm(): Later<Unit> {
        ui.value = ConfirmationState.Executing
        TODO("Not yet implemented")
    }
}