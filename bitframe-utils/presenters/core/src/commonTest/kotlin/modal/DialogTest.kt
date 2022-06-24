package modal

import expect.*
import kotlinx.coroutines.test.runTest
import presenters.modal.click
import presenters.modal.confirmDialog
import presenters.modal.formDialog
import kotlin.test.Test

class DialogTest {

    @Test
    fun should_be_able_to_create_a_dialog() {
        val clicks = mutableListOf<String>()
        val d = confirmDialog(
            heading = "Test Dialog",
            details = "This is a test dialog",
        ) {
            on("Cancel") {
                clicks.add("cancelled")
            }
            on("Delete") {
                clicks.add("deleted")
            }
            onConfirm { }
        }

        d.click("Cancel")
        expectCollection(clicks).toContain("cancelled")

        d.click("Delete")
        expectCollection(clicks).toContain("deleted")
    }

    @Test
    fun should_return_failure_when_trying_to_click_an_incorrect_action() = runTest {
        val d = formDialog(
            heading = "Test dialog",
            details = "Test details",
            fields = "Basic"
        ) {
            on("Cancel") { println("Canceled") }
            on("Delete") { }
            onSubmit { p: Unit -> }
        }
//        expectFailure {
//            d.click("OK")
//        }
    }
}