package bitframe.authentication.signup

import bitframe.presenters.feedbacks.FormFeedback

fun SignUpState.IndividualForm.copy(
    i: SignUpIntent.Submit.IndividualForm, status: FormFeedback?
) = copy(
    fields = fields.copy(i), status = status
)

fun SignUpState.BusinessForm.copy(
    i: SignUpIntent.Submit.BusinessForm, status: FormFeedback?
) = copy(
    fields = fields.copy(i), status = status
)

fun SignUpState.copy(i: SignUpIntent.Submit.BusinessForm, status: FormFeedback?) = when (this) {
    is SignUpState.IndividualForm -> error("Can't submit a business form while the viewmodel is in individual form state")
    is SignUpState.BusinessForm -> copy(i, status)
}

fun SignUpState.copy(i: SignUpIntent.Submit.IndividualForm, status: FormFeedback?) = when (this) {
    is SignUpState.IndividualForm -> copy(i, status)
    is SignUpState.BusinessForm -> error("Can't submit an individual's form while the viewmodel is in business form state")
}

fun SignUpState.copy(i: SignUpIntent.Submit, status: FormFeedback?) = when (i) {
    is SignUpIntent.Submit.IndividualForm -> copy(i, status)
    is SignUpIntent.Submit.BusinessForm -> copy(i, status)
}