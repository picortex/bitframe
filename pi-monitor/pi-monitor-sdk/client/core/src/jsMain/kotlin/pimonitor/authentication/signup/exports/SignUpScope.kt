@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.authentication.signup.exports

import bitframe.client.UIScope
import pimonitor.PiMonitorScopeConfig
import pimonitor.api.PiMonitorService
import pimonitor.authentication.signup.SignUpViewModel
import viewmodel.ViewModel
import pimonitor.authentication.signup.SignUpIntent as Intent
import pimonitor.authentication.signup.SignUpState as State

open class SignUpScope(
    config: PiMonitorScopeConfig
) : UIScope<Intent, State> {
    override val service: PiMonitorService = config.service

    override val viewModel: ViewModel<Intent, State> by lazy { SignUpViewModel(config) }

    val TYPE_BUSINESS get() = State.REGISTER_AS_BUSINESS.value

    val TYPE_INDIVIDUAL get() = State.REGISTER_AS_INDIVIDUAL.value

    val changeRegistrationType = { type: String ->
        viewModel.post(Intent.ChangeRegistrationType(type))
    }

    val submitIndividualForm = { params: RegisterIndividualParams ->
        viewModel.post(Intent.Submit.IndividualForm(params.toSignUpParams()))
    }

    val submitBusinessForm = { params: RegisterBusinessParams ->
        viewModel.post(Intent.Submit.BusinessForm(params.toSignUpParams()))
    }
}