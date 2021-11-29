package bitframe.authentication.signup

fun BusinessFormFields.copy(i: SignUpIntent.Submit.BusinessForm) = copy(
    businessName = businessName.copy(value = i.params.businessName),
    individualName = individualName.copy(value = i.params.individualName),
    individualEmail = individualEmail.copy(value = i.params.individualEmail),
    password = password.copy(value = i.params.password)
)