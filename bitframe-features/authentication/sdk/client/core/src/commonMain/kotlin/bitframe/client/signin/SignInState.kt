@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client.signin

import bitframe.core.Space
import bitframe.core.User
import kotlinx.collections.interoperable.List
import presenters.cases.Feedback
import presenters.cases.Feedback.Failure
import presenters.cases.Feedback.Success
import presenters.cases.Feedback.Loading

import kotlin.js.JsExport

sealed class SignInState {
    data class Form(
        /**
         * The Sign In Form
         */
        val data: SignInForm,
        /**
         * The status of a form whether it is [Loading], a [Success], or a [Failure].
         * if it is null, then there is no status to give and nothing is happening in the background
         */
        val status: Feedback = Feedback.None
    ) : SignInState()

    data class Conundrum(
        val user: User,
        val spaces: List<Space>,
        val status: Feedback = Feedback.None
    ) : SignInState()
}