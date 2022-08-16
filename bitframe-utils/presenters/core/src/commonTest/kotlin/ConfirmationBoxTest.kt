import live.expect
import presenters.confirmations.ConfirmationState
import presenters.confirmations.internal.ConfirmationBoxImpl
import viewmodel.ScopeConfig
import kotlin.test.Test

class ConfirmationBoxTest {
    @Test
    fun a_confirmation_box_should_start_in_a_pending_state() {
        val box = ConfirmationBoxImpl(
            heading = "Delete George",
            details = "Are you sure you want to delete George?",
            config = ScopeConfig(Unit)
        )
        expect(box.ui).toBeIn(ConfirmationState.Pending)
    }
}