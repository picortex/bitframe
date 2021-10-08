package pimonitor

import bitframe.authentication.signin.SignInServiceKtor
import bitframe.authentication.users.UsersServiceKtor
import bitframe.service.config.KtorClientConfiguration
import pimonitor.authentication.signup.SignUpServiceKtor
import pimonitor.evaluation.businesses.BusinessServiceKtor

class PiMonitorServiceKtor(
    private val configuration: KtorClientConfiguration
) : PiMonitorService(
    users = UsersServiceKtor(configuration),
    signIn = SignInServiceKtor(configuration),
    signUp = SignUpServiceKtor(configuration),
    businesses = BusinessServiceKtor((configuration))
)