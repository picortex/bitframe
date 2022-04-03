package bitframe.client.signin

import presenters.cases.Feedback

internal fun SignInState.copy(cause: Throwable) = when (this) {
    is SignInState.Form -> copy(status = Feedback.Failure(cause))
    is SignInState.Conundrum -> copy(status = Feedback.Failure(cause))
}