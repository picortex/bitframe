package bitframe.daos.config

import bitframe.core.MockDaoConfig
import bitframe.core.MockDaoFactoryConfig

fun DatabaseConfigurationMock.toDaoFactoryMockConfig() = MockDaoFactoryConfig(
    simulationTime = simulationTime ?: MockDaoConfig.DEFAULT_SIMULATION_TIME
)