package bitframe.server.types

actual inline fun <reified T> processTypes(): TypeInfo {
    return processKotlinClass<T>()
}

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T> processKotlinClass(): TypeInfo {
    val clazz = T::class
    val exception = Exception("Failed to process name for ${T::class.simpleName}")
    val singular = clazz.simpleName?.lowercase() ?: throw exception
    val plural = "${singular}s"
    return TypeInfo(singular, plural, listOf())
}