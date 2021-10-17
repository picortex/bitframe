package pimonitor.evaluation.business.forms

import bitframe.presenters.fields.ButtonInputField
import bitframe.presenters.fields.TextInputField
import pimonitor.monitors.Monitor

internal fun AddBusinessFormFields() = CreateBusinessFields(
    inviterIntroduction = null,
    title = "Add a business",
    businessName = TextInputField(
        label = "Business name",
        hint = "PiCortex LLC"
    ),
    contactName = TextInputField(
        label = "Contact Name",
        hint = "John Doe"
    ),
    contactEmail = TextInputField(
        label = "Contact Email",
        hint = "john@doe.com"
    ),
    submitButton = ButtonInputField(text = "Submit")
)

internal fun InviteBusinessFormFields(inviter: Monitor) = CreateBusinessFields(
    inviterIntroduction = "${inviter.name} has invited to you to share your info with them",
    title = "Enter your business info",
    businessName = TextInputField(
        label = "Business name",
        hint = "PiCortex LLC"
    ),
    contactName = TextInputField(
        label = "Your Name",
        hint = "John Doe"
    ),
    contactEmail = TextInputField(
        label = "Your Email",
        hint = "john@doe.com"
    ),
    submitButton = ButtonInputField(text = "Submit")
)