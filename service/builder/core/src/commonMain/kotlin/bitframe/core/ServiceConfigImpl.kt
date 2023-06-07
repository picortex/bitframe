package bitframe.core

import events.EventBus
import kotlinx.coroutines.CoroutineScope
import lexi.Logger

class ServiceConfigImpl(
    override val bus: EventBus = ServiceConfig.DEFAULT_BUS,
    override val logger: Logger = ServiceConfig.DEFAULT_LOGGER,
    override val scope: CoroutineScope = ServiceConfig.DEFAULT_SCOPE
) : ServiceConfig