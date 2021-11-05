package integration

import bitframe.authentication.AuthenticationDaoProvider
import bitframe.authentication.InMemoryAuthenticationDaoProvider

class TestBitframeDaoProvider(
    authDaoProvider: AuthenticationDaoProvider = InMemoryAuthenticationDaoProvider()
): DAOProvider, AuthenticationDaoProvider by authDaoProvider