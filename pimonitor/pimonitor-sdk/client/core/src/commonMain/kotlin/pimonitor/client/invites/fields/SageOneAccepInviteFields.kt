package pimonitor.client.invites.fields

import pimonitor.core.sage.AcceptSageOneInviteParams
import presenters.fields.ButtonInputField
import presenters.fields.TextInputField

data class SageOneAcceptInviteFields(
    val companyId: TextInputField = TextInputField(
        name = AcceptSageOneInviteParams::companyId.name,
        label = "Sage Company Id",
        hint = "XXXXXX"
    ),
    val username: TextInputField = TextInputField(
        name = AcceptSageOneInviteParams::username.name,
        label = "Sage Username",
        hint = "john"
    ),
    val password: TextInputField = TextInputField(
        name = AcceptSageOneInviteParams::password.name,
        label = "Sage Password",
        hint = "sage-secure-password"
    ),
    val send: ButtonInputField = ButtonInputField(
        name = "Send",
        text = "Submit"
    )
)