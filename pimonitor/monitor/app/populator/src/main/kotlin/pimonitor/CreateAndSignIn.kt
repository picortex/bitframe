package pimonitor

import bitframe.core.signin.SignInParams
import bitframe.core.signin.SignInResult
import later.await
import pimonitor.client.MonitorApi
import pimonitor.core.signup.params.SignUpIndividualParams

suspend fun MonitorApi.createAndSignIn(operator: Operator): SignInResult {
    val params = SignUpIndividualParams(
        name = operator.name,
        email = operator.email,
        password = operator.email
    )
    signUp.signUp(params).await()

    val cred = SignInParams(
        identifier = operator.email,
        password = operator.email
    )
    return signIn.signIn(cred).await()
}