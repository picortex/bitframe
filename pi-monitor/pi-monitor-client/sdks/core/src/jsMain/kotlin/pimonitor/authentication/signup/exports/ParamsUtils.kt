package pimonitor.authentication.signup.exports

import pimonitor.monitors.SignUpParams

fun RegisterIndividualParams.toSignUpParams() = SignUpParams.Individual(name, email, password)

fun RegisterBusinessParams.toSignUpParams() = SignUpParams.Business(businessName, individualName, individualEmail, password)