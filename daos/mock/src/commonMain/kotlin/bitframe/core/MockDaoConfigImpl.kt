package bitframe.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Mutex
import kotlin.reflect.KClass

@Deprecated("In favour of bitframe.dao.internal.DaoMockConfig")
internal class MockDaoConfigImpl<D : Any>(
    override val clazz: KClass<D>,
    override val items: MutableMap<String, D> = mutableMapOf(),
    override val simulationTime: Long = MockDaoConfig.DEFAULT_SIMULATION_TIME,
    override val namespace: String = clazz.simpleName?.lowercase() ?: MockDaoConfig.DEFAULT_NAMESPACE,
    override val prefix: String = clazz.simpleName?.lowercase() ?: MockDaoConfig.DEFAULT_PREFIX,
    override val lock: Mutex = MockDaoConfig.DEFAULT_LOCK,
    override val scope: CoroutineScope = MockDaoConfig.DEFAULT_SCOPE,
) : MockDaoConfig<D>