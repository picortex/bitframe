@file:JsExport

package pimonitor.authentication.signup

import pimonitor.MonitorParams
import pimonitor.PiMonitorService
import pimonitor.authentication.SignUpService
import kotlin.reflect.KFunction1
import pimonitor.authentication.signup.SignUpIntent as Intent


external interface Params {
    var name: String?
    var email: String?
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

    val submitForm = { params: Params ->
        viewModel.post(Intent.SubmitForm(params.toMonitorParams()))
    }

    private fun Params.toMonitorParams() = MonitorParams(name, email)
}