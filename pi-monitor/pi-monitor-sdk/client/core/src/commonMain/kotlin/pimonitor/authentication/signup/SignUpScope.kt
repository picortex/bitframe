@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.authentication.signup

import pimonitor.PiMonitorScopeConfig
import pimonitor.api.PiMonitorApi
import viewmodel.ViewModel
import kotlin.js.JsExport
import pimonitor.authentication.signup.SignUpIntent as Intent
import pimonitor.authentication.signup.SignUpState as State

open class SignUpScope(
    config: PiMonitorScopeConfig
) : UIScope<Intent, State> {
    override val api: PiMonitorApi = config.api

    override val viewModel: ViewModel<Intent, State> by lazy { SignUpViewModel(config) }

    val TYPE_BUSINESS: SignUpType get() = State.REGISTER_AS_BUSINESS.value

    val TYPE_INDIVIDUAL: SignUpType get() = State.REGISTER_AS_INDIVIDUAL.value

    val changeRegistrationType = { type: SignUpType ->
        viewModel.post(Intent.ChangeRegistrationType(type))
    }

    val submitIndividualForm = { params: IRawIndividualSignUpParams ->
        viewModel.post(Intent.Submit.IndividualForm(params))
    }

    val submitBusinessForm = { params: IRawBusinessSignUpParams ->
        viewModel.post(Intent.Submit.BusinessForm(params))
    }
}