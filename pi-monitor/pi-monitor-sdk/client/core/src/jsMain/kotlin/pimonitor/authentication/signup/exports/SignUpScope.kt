@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.authentication.signup.exports

import bitframe.client.UIScope
import pimonitor.PiMonitorViewModelConfig
import pimonitor.api.PiMonitorService
import pimonitor.authentication.signup.SignUpViewModel
import viewmodel.ViewModel
import pimonitor.authentication.signup.SignUpIntent as Intent
import pimonitor.authentication.signup.SignUpState as State

open class SignUpScope(
    config: PiMonitorViewModelConfig
) : UIScope<Intent, State> {
    override val viewModel: ViewModel<Intent, State> by lazy { SignUpViewModel(config) }

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
}