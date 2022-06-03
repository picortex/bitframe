package authenticator

import bitframe.client.ServiceConfigMock

internal class UsersServiceMock(
    val config: ServiceConfigMock
) : UsersService(config), UsersServiceCore by UsersServiceDaod(config)