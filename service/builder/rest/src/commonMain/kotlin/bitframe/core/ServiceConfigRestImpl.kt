package bitframe.core

import events.EventBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import lexi.Logger

@Deprecated("")
class ServiceConfigRestImpl<out E>(
    override val endpoint: E,
    override val bus: EventBus = ServiceConfig.DEFAULT_BUS,
    override val logger: Logger = ServiceConfig.DEFAULT_LOGGER,
    override val scope: CoroutineScope = ServiceConfig.DEFAULT_SCOPE,
    override val json: Json = ServiceConfigRest.DEFAULT_JSON,
) : ServiceConfigRest<E>