package cache.exceptions

class CacheLoadException(
    override val message: String = "Failed to save load from the cache",
    override val cause: Throwable? = null
) : CacheException(message, cause)