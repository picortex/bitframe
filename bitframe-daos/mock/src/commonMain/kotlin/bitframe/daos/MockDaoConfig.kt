package bitframe.daos

import bitframe.daos.config.DaoConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex
import kotlin.jvm.JvmField
import kotlin.reflect.KClass

interface MockDaoConfig<D : Any> : DaoConfig {
    val clazz: KClass<D>
    val items: MutableMap<String, D>
    val simulationTime: Long
    val lock: Mutex
    val namespace: String
    val prefix: String

    companion object {

        /**
         * The safe minimum simulation time for an InMemory Dao which is usually by a [List]
         */
        @JvmField
        val MIN_SIMULATION_TIME = 0L

        /**
         * The time (in milliseconds) for simulating data operations to get
         */
        @JvmField
        val DEFAULT_SIMULATION_TIME = MIN_SIMULATION_TIME

        @JvmField
        val DEFAULT_LOCK = Mutex()

        @JvmField
        val DEFAULT_NAMESPACE = ""

        @JvmField
        val DEFAULT_PREFIX = ""

        @JvmField
        val DEFAULT_SCOPE = DaoConfig.DEFAULT_SCOPE

        operator fun <D : Any> invoke(
            clazz: KClass<D>,
            items: MutableMap<String, D> = mutableMapOf(),
            simulationTime: Long = DEFAULT_SIMULATION_TIME,
            namespace: String? = null,
            prefix: String? = null,
            lock: Mutex = DEFAULT_LOCK,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): MockDaoConfig<D> = object : MockDaoConfig<D> {
            override val clazz: KClass<D> = clazz
            override val items: MutableMap<String, D> = items
            override val simulationTime: Long = simulationTime
            override val lock: Mutex = lock
            override val scope: CoroutineScope = scope
            override val namespace = namespace ?: clazz.simpleName?.lowercase() ?: DEFAULT_NAMESPACE
            override val prefix = prefix ?: clazz.simpleName?.lowercase() ?: DEFAULT_NAMESPACE
        }

        inline operator fun <reified D : Any> invoke(
            items: MutableMap<String, D> = mutableMapOf(),
            simulationTime: Long = DEFAULT_SIMULATION_TIME,
            namespace: String? = null,
            prefix: String? = null,
            lock: Mutex = DEFAULT_LOCK,
            scope: CoroutineScope = DEFAULT_SCOPE
        ): MockDaoConfig<D> = invoke(D::class, items, simulationTime, namespace, prefix, lock, scope)
    }
}