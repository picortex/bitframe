@file:JsExport

package pimonitor.authentication.signup

import pimonitor.MonitorBusinessParams
import pimonitor.MonitorPersonParams
import pimonitor.authentication.SignUpService
import useViewModelState
import kotlin.reflect.KFunction1
import pimonitor.authentication.signup.SignUpIntent as Intent

external interface OrganisationParams {
    var name: String?
    var email: String?
}

external interface IndividualParams : OrganisationParams {
    var password: String?
}

private inline fun <F : KFunction1<*, *>> F.bind(obj: Any): F {
    asDynamic().bind(obj)
    return this
}

class SignUpScope(service: SignUpService) {

    val viewModel = SignUpViewModel(service)

    val selectRegistrationType = {
        viewModel.post(Intent.SelectRegistrationType)
    }

    val registerAsIndividual = {
        viewModel.post(Intent.RegisterAsIndividual(null))
    }

    val registerAsOrganisation = {
        viewModel.post(Intent.RegisterAsOrganization(null))
    }

    val submitIndividualForm = { params: IndividualParams ->
        viewModel.post(Intent.SubmitIndividualForm(params.toMonitorPersonParams()))
    }

    val submitOrganisationForm = { params: OrganisationParams ->
        viewModel.post(Intent.SubmitBusinessForm(params.toMonitorBusinessParams()))
    }

    private fun IndividualParams.toMonitorPersonParams() = MonitorPersonParams(name, email, password)
    private fun OrganisationParams.toMonitorBusinessParams() = MonitorBusinessParams(name, email)
}