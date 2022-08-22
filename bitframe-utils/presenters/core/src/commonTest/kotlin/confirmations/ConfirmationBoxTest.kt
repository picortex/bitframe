package confirmations

import koncurrent.Later
import live.expect
import expect.expect
import live.toHaveGoneThrough2
import presenters.confirmations.ConfirmationState
import presenters.confirmations.ConfirmationState.Executed
import presenters.confirmations.ConfirmationState.Executed.Exceptionally
import presenters.confirmations.ConfirmationState.Executed.Successfully
import presenters.confirmations.ConfirmationState.Executing
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
        ) {
            onSubmit {
                Later.resolve(5)
            }
        }
        expect(box.ui).toBeIn(ConfirmationState.Pending)
    }

    @Test
    fun a_confirmation_box_can_be_driven_to_a_successful_execution() {
        var cancelled = false
        var confirmed = false

        val box = ConfirmationBoxImpl(
            heading = "Delete George",
            details = "Are you sure you want to delete George?",
            config = ScopeConfig(Unit)
        ) {
            onCancel {
                cancelled = true
            }
            onSubmit {
                confirmed = true
                Later.resolve(confirmed)
            }
        }
        expect(cancelled).toBe(false)
        expect(confirmed).toBe(false)

        box.confirm()
        expect(box.ui).toHaveGoneThrough2<Executing, Successfully>()
        expect(cancelled).toBe(false)
        expect(confirmed).toBe(true)
    }

    @Test
    fun a_confirmation_box_can_be_driven_to_a_failed_execution() {
        var cancelled = false
        var confirmed = false

        val box = ConfirmationBoxImpl(
            heading = "Delete George",
            details = "Are you sure you want to delete George?",
            config = ScopeConfig(Unit)
        ) {
            onCancel {
                cancelled = true
            }
            onSubmit {
                confirmed = true
                Later.reject(RuntimeException("Rejecting for fun"))
            }
        }
        expect(cancelled).toBe(false)
        expect(confirmed).toBe(false)

        box.confirm()
        expect(box.ui).toHaveGoneThrough2<Executing, Exceptionally>()
        expect(cancelled).toBe(false)
        expect(confirmed).toBe(true)
    }
}