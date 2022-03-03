package businesses

import bitframe.core.signin.SignInCredentials
import expect.expect
import expect.toBe
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import later.await
import pimonitor.client.businesses.BusinessesDialogContent
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.signup.params.IndividualSignUpParams
import presenters.feedbacks.Feedback
import presenters.modal.click
import presenters.table.EmptyTable
import presenters.table.action
import presenters.table.click
import presenters.table.tabulateToConsole
import utils.PiMonitorMockScope
import viewmodel.expect
import kotlin.test.Test
import pimonitor.client.businesses.BusinessesIntent as Intent
import pimonitor.client.businesses.BusinessesState as State

class DeleteBusinessJourneyTest {

    val scope = PiMonitorMockScope()
    val api = scope.api
    val vm = scope.businesses.viewModel

    val state get() = vm.ui.value

    @Test
    fun should_delete_a_single_business_easily() = runTest {
        val time = Clock.System.now()
        // Step 1: Register as an individual monitor
        val params1 = IndividualSignUpParams(
            name = "John $time Doe",
            email = "john@doe$time.com",
            password = "johndoe"
        )
        val res1 = api.signUp.signUp(params1).await()
        expect(res1.user.name).toBe("John $time Doe")

        // Step 2: Sign in as the registered monitor
        val params2 = SignInCredentials(
            identifier = "john@doe$time.com",
            password = "johndoe"
        )
        api.signIn.signIn(params2).await()

        // Step 3: Create a monitored business
        val params3 = CreateMonitoredBusinessParams(
            businessName = "Test Business",
            contactEmail = "contact$time@test.com",
            contactName = "Jane Doe",
            sendInvite = true
        )
        api.businesses.create(params3).await()

        // Step 4: Load Businesses in the viewmodel
        vm.expect(Intent.LoadBusinesses)
        expect(state.table.rows.first().data.name).toBe("Test Business")

        // Step 5: Click delete on the specific row
        state.table.click("Delete", 1)
//        expect(state.dialog?.content).toBe(BusinessesDialogContent.Confirm)
        expect(state.dialog?.heading).toBe("Delete Business")

        // Step 6: Confirm Delete
        // Emulate clicking confirm dialog. Which triggers the delete intent: vm.ui.value.dialog?.click("Confirm")
        vm.expect(Intent.Delete(state.table.rows.first().data))
        expect(state.table).toBe<EmptyTable<*>>()
    }

    @Test
    fun should_delete_multiple_business_easily() = runTest {
        val time = Clock.System.now()
        // Step 1: Register as an individual monitor
        val params1 = IndividualSignUpParams(
            name = "John $time Doe",
            email = "john@doe$time.com",
            password = "johndoe"
        )
        val res1 = api.signUp.signUp(params1).await()
        expect(res1.user.name).toBe("John $time Doe")

        // Step 2: Sign in as the registered monitor
        val params2 = SignInCredentials(
            identifier = "john@doe$time.com",
            password = "johndoe"
        )
        api.signIn.signIn(params2).await()

        // Step 3: Create multiple monitored business
        repeat(10) {
            val params3 = CreateMonitoredBusinessParams(
                businessName = "Test Business M$it",
                contactEmail = "contact$time@test$it.com",
                contactName = "Jane Doe M$it",
                sendInvite = true
            )
            api.businesses.create(params3).await()
            delay(20)
        }

        // Step 4: Load Businesses in the viewmodel
        vm.expect(Intent.LoadBusinesses)
        expect(state.table.rows.first().data.name).toBe("Test Business M0")

        // Step 5: Click delete all
        state.table.selectAll()
        state.table.tabulateToConsole()
        state.table.action("Delete All")
//        expect(state.dialog?.content).toBe(BusinessesDialogContent.Confirm)
        expect(state.dialog?.heading).toBe("Delete Businesses")

        // Step 6: Confirm Delete
        // Emulate clicking confirm dialog. Which triggers the delete all intent: vm.ui.value.dialog?.click("Confirm")
        vm.expect(Intent.DeleteAll(state.table.allSelectedRows.map { it.data }.toTypedArray()))
        expect(state.table).toBe<EmptyTable<*>>()
    }
}