package pimonitor.authentication.signup.exports

import pimonitor.authentication.signup.SignUpParams

fun RegisterIndividualParams.toSignUpParams() = SignUpParams.Individual(name, email, password)

fun RegisterBusinessParams.toSignUpParams() = SignUpParams.Business(businessName, individualName, individualEmail, password)