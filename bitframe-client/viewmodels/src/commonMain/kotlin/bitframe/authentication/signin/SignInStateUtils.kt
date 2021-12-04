package bitframe.authentication.signin

import presenters.feedbacks.FormFeedback

internal fun SignInState.Form.copy(
    i: SignInIntent.Submit,
    status: FormFeedback? = this.status
) = copy(
    fields = fields.copy(i),
    status = status
)