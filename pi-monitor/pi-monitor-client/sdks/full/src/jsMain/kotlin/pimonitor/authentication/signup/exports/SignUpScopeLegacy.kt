@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.authentication.signup.exports

import pimonitor.authentication.signup.SignUpService
import pimonitor.authentication.signup.legacy.SignUpViewModel
import viewmodel.ViewModel
import pimonitor.authentication.signup.legacy.SignUpIntent as Intent
import pimonitor.authentication.signup.legacy.SignUpState as State

class SignUpScopeLegacy(service: SignUpService) : SignUpServiceWrapper(service) {

    val viewModel: ViewModel<Intent, State> = SignUpViewModel(service)

    val selectRegistrationType = {
        viewModel.post(Intent.SelectRegistrationType)
    }

    val registerAsIndividual = {
        viewModel.post(Intent.RegisterAsIndividual(null))
    }

    val registerAsOrganisation = {
        viewModel.post(Intent.RegisterAsOrganization(null))
    }

    val submitIndividualForm = { params: RegisterIndividualParams ->
        viewModel.post(Intent.SubmitIndividualForm(params.toIndividualRegistrationParams()))
    }

    val submitOrganisationForm = { params: RegisterOrganisationParams ->
        viewModel.post(Intent.SubmitBusinessForm(params.toMonitorBusinessParams()))
    }
}