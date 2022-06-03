package authenticator

import bitframe.client.ServiceConfigMock
import bitframe.client.signin.SignInServiceMock
import bitframe.client.signout.SignOutService

class AuthenticatorApiMock(
    config: ServiceConfigMock = ServiceConfigMock()
) : AuthenticatorApi by AuthenticatorApiImpl(
    AuthenticatorServices(
        config = config,
        signIn = SignInServiceMock(config),
        signOut = SignOutService(config),
        users = UsersServiceMock(config)
    )
)