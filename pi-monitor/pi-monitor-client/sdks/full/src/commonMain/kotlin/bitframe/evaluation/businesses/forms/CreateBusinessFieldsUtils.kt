package bitframe.evaluation.businesses.forms

import bitframe.presenters.fields.BooleanInputField
import bitframe.presenters.fields.ButtonInputField
import bitframe.presenters.fields.TextInputField
import bitframe.monitors.Monitor
import bitframe.evaluation.businesses.forms.CreateBusinessIntent as Intent

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
    sendInvite = BooleanInputField(
        label = "Ask to share reports",
        value = true
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
    sendInvite = null,
    submitButton = ButtonInputField(text = "Submit")
)

internal fun CreateBusinessFields.copy(i: Intent.SubmitForm) = copy(
    businessName = businessName.copy(value = i.params.businessName),
    contactName = contactName.copy(value = i.params.contactName),
    contactEmail = contactEmail.copy(value = i.params.contactEmail),
    sendInvite = sendInvite?.copy(value = i.params.sendInvite)
)