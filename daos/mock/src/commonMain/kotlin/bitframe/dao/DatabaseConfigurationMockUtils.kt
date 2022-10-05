package bitframe.dao

import bitframe.DaoMockConfig
import bitframe.DaoFactoryMockConfig
import bitframe.DaoFactoryMockConfigRaw

fun DaoFactoryMockConfigRaw.toDaoFactoryMockConfig() = DaoFactoryMockConfig(
    simulationTime = simulationTime ?: DaoMockConfig.DEFAULT_SIMULATION_TIME
)