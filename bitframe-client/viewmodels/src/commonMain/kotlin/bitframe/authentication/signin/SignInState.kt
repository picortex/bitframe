package bitframe.authentication.signin

import bitframe.presenters.feedbacks.FormFeedback

data class SignInState(
    val fields: SignInFormFields,
    val status: FormFeedback?
)