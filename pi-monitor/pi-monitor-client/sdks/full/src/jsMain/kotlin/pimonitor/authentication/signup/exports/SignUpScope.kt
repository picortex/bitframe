@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.authentication.signup.exports

import pimonitor.PiMonitorService
import pimonitor.authentication.signup.SignUpResult
import pimonitor.authentication.signup.SignUpService
import pimonitor.authentication.signup.SignUpViewModel
import useEventHandler
import viewmodel.ViewModel
import pimonitor.authentication.signup.SignUpIntent as Intent
import pimonitor.authentication.signup.SignUpState as State

class SignUpScope(val service: SignUpService) : SignUpServiceWrapper(service) {

    val viewModel: ViewModel<Intent, State> = SignUpViewModel(service)

    val registerAsIndividual = {
        viewModel.post(Intent.SelectRegisterAsIndividual)
    }

    val registerAsBusiness = {
        viewModel.post(Intent.SelectRegisterAsBusiness)
    }

    val submitIndividualForm = { params: RegisterIndividualParams ->
        viewModel.post(Intent.Submit.IndividualForm(params.toSignUpParams()))
    }

    val submitBusinessForm = { params: RegisterBusinessParams ->
        viewModel.post(Intent.Submit.BusinessForm(params.toSignUpParams()))
    }

    val useSignUpEvent: (callback: (SignUpResult) -> Unit) -> Unit = {
        useEventHandler(service.bus, SignUpService.SIGN_UP_EVENT_ID, it)
    }
}