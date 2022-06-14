package bitframe

fun <S> ServerConfig<*>.toControllerConfigOf(service: S): ControllerConfig<S> = ControllerConfigImpl(service, json)