package businesses

import bitframe.core.signin.SignInParams
import expect.expect
import expect.toBe
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import later.await
import pimonitor.client.businesses.BusinessesViewModel
import pimonitor.client.runSequence
import pimonitor.core.businesses.params.CreateMonitoredBusinessParams
import pimonitor.core.signup.params.SignUpIndividualParams
import presenters.table.EmptyTable
import presenters.table.action
import presenters.table.click
import presenters.table.tabulateToConsole
import utils.PiMonitorTestScope
import viewmodel.expect
import kotlin.test.Test
import pimonitor.client.businesses.BusinessesIntent as Intent

class DeleteBusinessJourneyTest {

    private val scope = PiMonitorTestScope()
    private val api = scope.api
    private val vm = scope.businesses.viewModel as BusinessesViewModel

    val state get() = vm.ui.value

    @Test
    fun should_delete_a_single_business_easily() = runSequence {
        step("Register as an individual monitor") {
            val params1 = SignUpIndividualParams(
                name = "John $time Doe",
                email = "john@doe$time.com",
                password = "johndoe"
            )
            val res1 = api.signUp.signUp(params1).await()
            expect(res1.user.name).toBe("John $time Doe")
        }

        step("Sign in as the registered monitor") {
            val params2 = SignInParams(
                identifier = "john@doe$time.com",
                password = "johndoe"
            )
            api.signIn.signIn(params2).await()
        }

        step("Create multiple monitored business") {
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
        }

        step("Load Businesses in the viewmodel") {
            vm.expect(Intent.LoadBusinesses)
            expect(state.table.rows.first().data.name).toBe("Test Business M0")
        }

        step("Click delete on the specific row") {
            state.table.click("Delete", 1)
            // expect(state.dialog?.content).toBe(BusinessesDialogContent.Confirm)
            expect(state.dialog?.heading).toBe("Delete Business")
        }

        step("Confirm Delete") {
            // Emulate clicking confirm dialog. Which triggers the delete intent: vm.ui.value.dialog?.click("Confirm")
            vm.expect(Intent.Delete(state.table.rows.first().data))
            expect(state.table.rows).toBeOfSize(9)
        }
    }

    @Test
    fun should_delete_multiple_business_easily() = runSequence {
        step("Register as an individual monitor") {
            val params1 = SignUpIndividualParams(
                name = "John $time Doe",
                email = "john@doe$time.com",
                password = "johndoe"
            )
            val res1 = api.signUp.signUp(params1).await()
            expect(res1.user.name).toBe("John $time Doe")
        }

        step("Sign in as the registered monitor") {
            val params2 = SignInParams(
                identifier = "john@doe$time.com",
                password = "johndoe"
            )
            api.signIn.signIn(params2).await()
        }

        step("Create multiple monitored business") {
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
        }

        step("Load Businesses in the viewmodel") {
            vm.expect(Intent.LoadBusinesses)
            expect(state.table.rows.first().data.name).toBe("Test Business M0")
        }

        step("Click delete all") {
            state.table.selectAll()
            state.table.tabulateToConsole()
            state.table.action("Delete All")
            expect(state.dialog?.heading).toBe("Delete Businesses")
        }

        step("Confirm Delete") {
            // Emulate clicking confirm dialog. Which triggers the delete all intent: vm.ui.value.dialog?.click("Confirm")
            vm.expect(Intent.DeleteAll(state.table.allSelectedRows.map { it.data }.toTypedArray()))
            state.table.tabulateToConsole()
            expect(state.table).toBe<EmptyTable<*>>()
        }
    }
}