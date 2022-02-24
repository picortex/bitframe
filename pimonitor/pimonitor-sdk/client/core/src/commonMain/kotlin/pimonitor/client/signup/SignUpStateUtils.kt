package pimonitor.client.signup

import pimonitor.client.signup.fields.copy
import presenters.feedbacks.Feedback

fun SignUpState.IndividualForm.copy(
    i: SignUpIntent.Submit.IndividualForm, status: Feedback?
) = copy(
    fields = fields.copy(i), status = status
)

fun SignUpState.BusinessForm.copy(
    i: SignUpIntent.Submit.BusinessForm, status: Feedback?
) = copy(
    fields = fields.copy(i), status = status
)

fun SignUpState.copy(i: SignUpIntent.Submit.BusinessForm, status: Feedback?) = when (this) {
    is SignUpState.IndividualForm -> error("Can't submit a business form while the viewmodel is in individual form state")
    is SignUpState.BusinessForm -> copy(i, status)
}

fun SignUpState.copy(i: SignUpIntent.Submit.IndividualForm, status: Feedback?) = when (this) {
    is SignUpState.IndividualForm -> copy(i, status)
    is SignUpState.BusinessForm -> error("Can't submit an individual's form while the viewmodel is in business form state")
}

fun SignUpState.copy(i: SignUpIntent.Submit, status: Feedback?) = when (i) {
    is SignUpIntent.Submit.IndividualForm -> copy(i, status)
    is SignUpIntent.Submit.BusinessForm -> copy(i, status)
}