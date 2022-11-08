package bitframe

import bitframe.internal.ControllerConfigImpl

fun <S> ServerConfig<*>.toControllerConfigOf(service: S): ControllerConfig<S> = ControllerConfigImpl(service, codec)

fun <S> ServiceConfigDaod.toControllerConfigOf(service: S): ControllerConfig<S> = ControllerConfigImpl(service, codec)