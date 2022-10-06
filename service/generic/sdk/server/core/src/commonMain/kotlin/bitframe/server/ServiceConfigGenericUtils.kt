package bitframe.server

inline fun <reified T : Any> ServiceConfig.toGenericServiceConfig() = GenericServiceConfig(
    T::class, daoFactory, bus, logger, mailer, json, scope
)