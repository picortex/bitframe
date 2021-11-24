package bitframe.authentication.signup.exports

import bitframe.authentication.signup.SignUpParams

fun RegisterIndividualParams.toSignUpParams() = SignUpParams.Individual(name, email, password)

fun RegisterBusinessParams.toSignUpParams() = SignUpParams.Business(businessName, individualName, individualEmail, password)