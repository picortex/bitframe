package bitframe.authentication.signup

fun IndividualFormFields.copy(i: SignUpIntent.Submit.IndividualForm) = copy(
    name = name.copy(value = i.params.name),
    email = email.copy(value = i.params.email),
    password = password.copy(value = i.params.password)
)