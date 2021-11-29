@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.authentication.signup.exports

import bitframe.authentication.signin.Session
import bitframe.authentication.client.signin.SignInService
import bitframe.client.PiMonitorService
import bitframe.authentication.signup.SignUpResult
import bitframe.authentication.signup.SignUpService
import bitframe.authentication.signup.SignUpViewModel
import useEventHandler
import viewmodel.ViewModel
import bitframe.authentication.signup.SignUpIntent as Intent
import bitframe.authentication.signup.SignUpState as State

class SignUpScope(
    private val service: PiMonitorService
) {
    val viewModel: ViewModel<Intent, State> by lazy { SignUpViewModel(service.signUp, service.signIn) }

    val registerAsIndividual = {
        viewModel.post(Intent.SelectRegisterAsIndividual)
    }

    val registerAsBusiness = {
        viewModel.post(Intent.SelectRegisterAsBusiness)
    }

    val changeRegistrationType = { type: String ->
        viewModel.post(Intent.ChangeRegistrationType(type))
    }

    val submitIndividualForm = { params: RegisterIndividualParams ->
        viewModel.post(Intent.Submit.IndividualForm(params.toSignUpParams()))
    }

    val submitBusinessForm = { params: RegisterBusinessParams ->
        viewModel.post(Intent.Submit.BusinessForm(params.toSignUpParams()))
    }

    val useSignUpEvent: (callback: (SignUpResult) -> Unit) -> Unit = {
        useEventHandler(service.signIn.config.bus, SignUpService.SIGN_UP_EVENT_ID, it)
    }

    val useSignInEvent: (callback: (Session.SignedIn) -> Unit) -> Unit = {
        useEventHandler(service.signIn.config.bus, SignInService.SIGN_IN_EVENT_ID, it)
    }
}