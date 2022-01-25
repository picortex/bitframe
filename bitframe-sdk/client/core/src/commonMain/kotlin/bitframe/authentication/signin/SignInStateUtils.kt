package bitframe.authentication.signin

import presenters.feedbacks.FormFeedback

internal fun SignInState.Form.copy(
    i: SignInIntent.Submit,
    status: FormFeedback? = this.status
) = copy(
    fields = fields.copy(i),
    status = status
)

internal fun SignInState.copy(cause: Throwable) = when (this) {
    is SignInState.Form -> copy(status = FormFeedback.Failure(cause))
    is SignInState.Conundrum -> copy(status = FormFeedback.Failure(cause))
}