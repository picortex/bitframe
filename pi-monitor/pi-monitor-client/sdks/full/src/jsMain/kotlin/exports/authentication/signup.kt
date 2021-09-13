@file:JsExport
@file:Suppress("PackageDirectoryMismatch")

import pimonitor.MonitorParams
import pimonitor.PiMonitorService
import pimonitor.authentication.signup.SignUpIntent
import pimonitor.authentication.signup.SignUpViewModel

external interface Params {
    var name: String?
    var email: String?
}

fun signUp(service: PiMonitorService) = SignUpScope(service)

class SignUpScope(service: PiMonitorService) {

    val viewModel = SignUpViewModel(service.signUp)

    fun goToStage1Intent() = SignUpIntent.Stage01
    val goToStage01 = { viewModel.post(goToStage1Intent()) }

    fun goToStage2Intent(business: Params) = SignUpIntent.Stage02(business.toMonitorParams())
    val goToStage02 = { business: Params -> viewModel.post(goToStage2Intent(business)) }

    fun submitIntent(person: Params) = SignUpIntent.Submit(person.toMonitorParams())
    val submit = { person: Params -> viewModel.post(submitIntent(person)) }

    private fun Params.toMonitorParams() = MonitorParams(name, email)
}