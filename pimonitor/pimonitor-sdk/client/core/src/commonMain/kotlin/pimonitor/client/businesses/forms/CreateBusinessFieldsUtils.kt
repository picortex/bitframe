package pimonitor.client.businesses.forms

import presenters.fields.BooleanInputField
import presenters.fields.ButtonInputField
import presenters.fields.TextInputField
import pimonitor.client.businesses.forms.CreateBusinessIntent as Intent

internal fun AddBusinessFormFields() = CreateBusinessFields(
    inviterIntroduction = null,
    title = "Add a business",
    businessName = TextInputField(
        name = "businessName",
        label = "Business name",
        hint = "PiCortex LLC"
    ),
    contactName = TextInputField(
        name = "contactName",
        label = "Contact Name",
        hint = "John Doe"
    ),
    contactEmail = TextInputField(
        name = "contactEmail",
        label = "Contact Email",
        hint = "john@doe.com"
    ),
    sendInvite = BooleanInputField(
        name = "sendInvite",
        label = "Ask to share reports",
        value = true
    ),
    submitButton = ButtonInputField(text = "Submit")
)

internal fun CreateBusinessFields.copy(i: Intent.SubmitForm) = copy(
    businessName = businessName.copy(value = i.params.businessName),
    contactName = contactName.copy(value = i.params.contactName),
    contactEmail = contactEmail.copy(value = i.params.contactEmail),
    sendInvite = sendInvite?.copy(value = i.params.sendInvite)
)