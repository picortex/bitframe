@file:Suppress("NOTHING_TO_INLINE", "FunctionName")

package bitframe.daos.config

import bitframe.core.MockDaoFactory

@Deprecated("In favour of bitframe.DaoFactoryMock")
inline fun DaoFactoryMock(configuration: DatabaseConfigurationRawMock) = MockDaoFactory(configuration.toDaoFactoryMockConfig())