package unit.authentication

import bitframe.authentication.Credentials
import bitframe.authentication.LoginViewModel
import bitframe.authentication.LoginViewModel.Intent.ShowForm
import bitframe.authentication.LoginViewModel.State
import bitframe.authentication.TestLoginService
import test.asyncTest
import viewmodel.test
import kotlin.test.Test

class LoginViewModelTest {
    private val service = TestLoginService()
    private val vm = LoginViewModel(service)

    @Test
    fun should_be_in_a_show_form_state_with_null_credentials_when_intent_with_null_credentials_is_posted() = asyncTest {
        vm.test(intent = ShowForm(null), toHave = State.Form(null))
    }

    @Test
    fun should_be_in_a_show_form_state_with_credentials_passed_in_the_intent() = asyncTest {
        val credentials = Credentials("username", "password")
        vm.test(intent = ShowForm(credentials), toHave = State.Form(credentials))
    }
}