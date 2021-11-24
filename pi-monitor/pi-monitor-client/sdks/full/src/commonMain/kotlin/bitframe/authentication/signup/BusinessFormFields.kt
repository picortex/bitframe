@file:JsExport

package bitframe.authentication.signup

import bitframe.presenters.fields.ButtonInputField
import bitframe.presenters.fields.DropDownInputField
import bitframe.presenters.fields.TextInputField
import bitframe.authentication.signup.SignUpState.Companion.REGISTER_AS_BUSINESS
import bitframe.authentication.signup.SignUpState.Companion.REGISTER_AS_INDIVIDUAL
import kotlin.js.JsExport

data class BusinessFormFields(
    val title: String = "Create Your Account",
    val select: DropDownInputField = DropDownInputField(
        REGISTER_AS_BUSINESS.copy(selected = true),
        REGISTER_AS_INDIVIDUAL
    ),
    val businessName: TextInputField = TextInputField("Business Name"),
    val individualName: TextInputField = TextInputField("Your Name"),
    val individualEmail: TextInputField = TextInputField("Your Email"),
    val password: TextInputField = TextInputField("Password"),
    val submitButton: ButtonInputField = ButtonInputField("Get Started"),
)