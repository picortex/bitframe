package bitframe.client.signin

import presenters.feedbacks.Feedback

internal fun SignInState.Form.copy(
    i: SignInIntent.Submit,
    status: Feedback? = this.status
) = copy(
    fields = fields.copy(i),
    status = status
)

internal fun SignInState.copy(cause: Throwable) = when (this) {
    is SignInState.Form -> copy(status = Feedback.Failure(cause))
    is SignInState.Conundrum -> copy(status = Feedback.Failure(cause))
}