package bitframe.client

inline fun <T> jso(builder: T.() -> Unit): T = js("{}").unsafeCast<T>().apply(builder)