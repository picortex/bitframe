package bitframe.authentication.signin

import bitframe.presenters.feedbacks.FormFeedback

internal fun SignInState.copy(
    i: SignInIntent.Submit,
    status: FormFeedback? = this.status
) = copy(
    fields = fields.copy(i),
    status = status
)