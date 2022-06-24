package bitframe

import bitframe.dao.internal.DaoMockConfigImpl
import koncurrent.Executor
import koncurrent.MockExecutor
import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import kotlin.reflect.KClass

interface DaoMockConfig<D : Any> : DaoConfig<D> {
    val items: MutableMap<String, D>
    val simulationTime: Long
    val namespace: String
    val prefix: String

    companion object {
        /**
         * The time (in milliseconds) for simulating data operations to get
         */
        @JvmField
        val DEFAULT_SIMULATION_TIME = 10L

        @JvmField
        val DEFAULT_NAMESPACE = ""

        @JvmField
        val DEFAULT_PREFIX = ""

        @JvmField
        val DEFAULT_EXECUTOR = MockExecutor()

        @JvmOverloads
        @JvmStatic
        @JvmName("create")
        operator fun <D : Any> invoke(
            clazz: KClass<D>,
            executor: Executor = DEFAULT_EXECUTOR,
            items: MutableMap<String, D> = mutableMapOf(),
            simulationTime: Long = DEFAULT_SIMULATION_TIME,
            namespace: String? = null,
            prefix: String? = null,
        ): DaoMockConfig<D> = DaoMockConfigImpl(
            clazz = clazz,
            executor = executor,
            items = items,
            simulationTime = simulationTime,
            namespace = namespace ?: clazz.simpleName?.lowercase() ?: DEFAULT_NAMESPACE,
            prefix = prefix ?: clazz.simpleName?.lowercase() ?: DEFAULT_PREFIX,
        )
    }
}