package bitframe

fun <T : Any> T.keyOf(id: String) = "${this::class.simpleName}.$id".lowercase()