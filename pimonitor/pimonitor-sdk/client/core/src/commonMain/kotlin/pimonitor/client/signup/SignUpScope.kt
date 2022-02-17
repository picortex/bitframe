@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.signup

import bitframe.client.UIScope
import bitframe.client.UIScopeConfig
import pimonitor.client.PiMonitorApi
import pimonitor.core.signup.IRawBusinessSignUpParams
import pimonitor.core.signup.IRawIndividualSignUpParams
import viewmodel.ViewModel
import kotlin.js.JsExport
import pimonitor.client.signup.SignUpIntent as Intent
import pimonitor.client.signup.SignUpState as State

open class SignUpScope(
    override val config: UIScopeConfig<PiMonitorApi>
) : UIScope<Intent, State> {

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