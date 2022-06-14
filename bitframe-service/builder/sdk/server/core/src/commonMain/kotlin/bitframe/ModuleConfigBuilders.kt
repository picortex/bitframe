package bitframe

inline fun <C, E> ServerConfig<E>.toModuleConfigOf(controller: C): ModuleConfig<C, E> = ModuleConfig(controller, endpoint)