package integration

import bitframe.authentication.AuthenticationDaoProvider
import bitframe.authentication.InMemoryAuthenticationDaoProvider
import bitframe.server.BitframeDaoProvider
import bitframe.server.data.DAOProvider

class TestBitframeDaoProvider(
    authDaoProvider: AuthenticationDaoProvider = InMemoryAuthenticationDaoProvider()
): DAOProvider, AuthenticationDaoProvider by authDaoProvider