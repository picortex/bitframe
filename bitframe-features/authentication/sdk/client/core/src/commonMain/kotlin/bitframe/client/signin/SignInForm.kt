@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client.signin

import bitframe.core.signin.SignInRawParams
import presenters.forms.Form
import presenters.forms.FormActionsBuildingBlock
import kotlin.js.JsExport

class SignInForm(
    email: String? = null,
    password: String? = null,
    block: FormActionsBuildingBlock<SignInRawParams>
) : Form<SignInFields, SignInRawParams> by Form(
    heading = "Sign In",
    details = "Log in to your space",
    fields = SignInFields(email, password),
    block
)