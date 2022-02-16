@file:JsExport

package bitframe.client.signin

import bitframe.core.Space
import bitframe.core.User
import kotlinx.collections.interoperable.List
import presenters.feedbacks.Feedback
import presenters.feedbacks.Feedback.Failure
import presenters.feedbacks.Feedback.Success
import presenters.feedbacks.Feedback.Loading

import kotlin.js.JsExport

sealed class SignInState {
    data class Form(
        /**
         * All the fields and the info to display on the sign in form
         */
        val fields: SignInFormFields,
        /**
         * The status of a form whether it is [Loading], a [Success], or a [Failure].
         * if it is null, then there is no status to give and nothing is happening in the background
         */
        val status: Feedback?
    ) : SignInState()

    data class Conundrum(
        val user: User,
        val spaces: List<Space>,
        val status: Feedback?
    ) : SignInState()
}