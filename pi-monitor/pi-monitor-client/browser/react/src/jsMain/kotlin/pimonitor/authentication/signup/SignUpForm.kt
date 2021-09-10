package pimonitor.authentication.signup

import kotlinx.css.em
import kotlinx.css.padding
import kotlinx.css.pct
import kotlinx.extensions.onDesktop
import kotlinx.extensions.onMobile
import react.RBuilder
import reakt.FlexBox
import reakt.centerContent
import styled.css

fun RBuilder.SignUpForm(viewModel: SignUpViewModel, state: SignUpState.Form) = FlexBox {
    css {
        centerContent()
        onMobile { padding(horizontal = 1.em) }
        onDesktop { padding(horizontal = 20.pct) }
    }
    when (state) {
        is SignUpState.Form.Stage01 -> Stage01Form(
            business = state.business,
            onCancel = {},
            onNext = { viewModel.post(SignUpIntent.Stage02(it)) }
        )
        is SignUpState.Form.Stage02 -> Stage02Form(
            person = state.person,
            onCancel = { viewModel.post(SignUpIntent.Stage01) },
            onNext = { viewModel.post(SignUpIntent.Submit(it)) }
        )
    }
}