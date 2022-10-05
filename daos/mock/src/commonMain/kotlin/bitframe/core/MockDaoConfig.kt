package bitframe.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex
import kotlin.jvm.JvmField
import kotlin.reflect.KClass

@Deprecated("In favour of bitframe.MockDaoConfig")
interface MockDaoConfig<D : Any> : DaoConfig<D> {
    val items: MutableMap<String, D>
    val simulationTime: Long
    val lock: Mutex
    val namespace: String
    val prefix: String

    companion object {
        /**
         * The time (in milliseconds) for simulating data operations to get
         */
        @JvmField
        val DEFAULT_SIMULATION_TIME = 10L

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
        ): MockDaoConfig<D> = MockDaoConfigImpl(
            clazz = clazz,
            items = items,
            simulationTime = simulationTime,
            lock = lock,
            scope = scope,
            namespace = namespace ?: clazz.simpleName?.lowercase() ?: DEFAULT_NAMESPACE,
            prefix = prefix ?: clazz.simpleName?.lowercase() ?: DEFAULT_PREFIX,
        )

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