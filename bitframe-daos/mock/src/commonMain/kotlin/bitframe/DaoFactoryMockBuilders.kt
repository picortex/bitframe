package bitframe

import bitframe.dao.toDaoFactoryMockConfig

inline fun DaoFactoryMock(configuration: DaoFactoryMockConfigRaw) = DaoFactoryMock(configuration.toDaoFactoryMockConfig())