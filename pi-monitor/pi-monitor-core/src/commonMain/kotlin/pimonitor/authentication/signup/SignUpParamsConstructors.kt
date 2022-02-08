package pimonitor.authentication.signup

fun SignUpParams(
    name: String,
    email: String,
    password: String
) = SignUpParams.Individual(name, email, password)

fun SignUpParams(
    businessName: String,
    individualName: String,
    individualEmail: String,
    password: String
) = SignUpParams.Business(businessName, individualName, individualEmail, password)