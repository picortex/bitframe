package modal

import expect.expect
import expect.expectFailure
import kotlinx.coroutines.test.runTest
import presenters.modal.click
import presenters.modal.dialog
import kotlin.test.Test

class DialogTest {

    @Test
    fun should_be_able_to_create_a_dialog() {
        val clicks = mutableListOf<String>()
        val d = dialog(
            heading = "Test Dialog",
            details = "This is a test dialog",
            content = 45
        ) {
            on("Cancel") {
                clicks.add("cancelled")
            }
            on("Delete") {
                clicks.add("deleted")
            }
        }

        d.click("Cancel")
        expect(clicks).toContain("cancelled")

        d.click("Delete")
        expect(clicks).toContain("deleted")
    }

    @Test
    fun should_return_failure_when_trying_to_click_an_incorrect_action() = runTest {
        val d = dialog(
            heading = "Test dialog",
            details = "Test details",
            content = "Basic"
        ) {
            on("Cancel") { println("Canceled") }
            on("Delete") { }
        }
        expectFailure {
            d.click("OK")
        }
    }
}