package bitframe

import kotlin.jvm.JvmName
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic

interface DaoFactoryMockConfig : DaoFactoryMockConfigRaw {
    override val simulationTime: Long

    companion object {
        @JvmStatic
        @JvmName("create")
        @JvmOverloads
        operator fun invoke(
            simulationTime: Long = DaoMockConfig.DEFAULT_SIMULATION_TIME,
        ): DaoFactoryMockConfig = object : DaoFactoryMockConfig {
            override val simulationTime = simulationTime
        }
    }
}