package bitframe.client

import bitframe.core.Savable

inline fun <reified T : Savable> GenericMockService(
    config: GenericMockServiceConfig<T>? = null
): GenericMockService<T> = GenericMockService(config ?: GenericMockServiceConfig())