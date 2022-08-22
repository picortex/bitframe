package presenters.confirmations.internal

import koncurrent.Later
import koncurrent.later.catch
import presenters.actions.ActionsBuilder
import presenters.actions.MutableSimpleAction
import presenters.actions.SimpleActionsBuilder
import presenters.confirmations.ConfirmationBox
import presenters.confirmations.ConfirmationState
import presenters.forms.FormActionsBuilder
import viewmodel.ScopeConfig
import viewmodel.ViewModel

class ConfirmationBoxImpl(
    override val heading: String,
    override val details: String,
    private val config: ScopeConfig<*>,
    private val actionsBuilder: FormActionsBuilder<Any?>.() -> Unit
) : ViewModel<ConfirmationState>(config.of(ConfirmationState.Pending)), ConfirmationBox {

    private val actions = FormActionsBuilder<Any?>().apply(actionsBuilder)

    override val cancelAction = MutableSimpleAction(
        name = "Cancel",
        handler = actions.actions.firstOrNull {
            it.name.contentEquals("cancel", ignoreCase = true)
        }?.handler ?: {
            logger.warn("Cancel hasn't been handled yet")
        }
    )

    private val confirmAction = actions.submitAction

    override fun confirm(): Later<Unit> {
        ui.value = ConfirmationState.Executing
        return confirmAction(Unit).then {
            ui.value = ConfirmationState.Executed.Successfully
        }.catch {
            ui.value = ConfirmationState.Executed.Exceptionally
        }
    }
}