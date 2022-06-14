package bitframe.daos.config

import bitframe.core.MockDaoConfig
import bitframe.core.MockDaoFactoryConfig

fun DatabaseConfigurationRawMock.toDaoFactoryMockConfig() = MockDaoFactoryConfig(
    simulationTime = simulationTime ?: MockDaoConfig.DEFAULT_SIMULATION_TIME
)