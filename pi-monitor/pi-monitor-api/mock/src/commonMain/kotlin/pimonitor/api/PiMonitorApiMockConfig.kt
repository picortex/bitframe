package pimonitor.api

import bitframe.client.BitframeApiMockConfig
import bitframe.core.daos.DaoFactory
import bitframe.core.service.Session
import bitframe.client.MockServiceConfig
import cache.Cache
import events.EventBus
import kotlinx.coroutines.CoroutineScope
import live.MutableLive
import logging.Logger
import kotlin.jvm.JvmOverloads

class PiMonitorApiMockConfig @JvmOverloads constructor(
    override val appId: String = MockServiceConfig.DEFAULT_APP_ID,
    override val cache: Cache = MockServiceConfig.DEFAULT_CACHE,
    override val session: MutableLive<Session> = MockServiceConfig.DEFAULT_LIVE_SESSION,
    override val daoFactory: DaoFactory = MockServiceConfig.DEFAULT_DAO_FACTORY,
    override val bus: EventBus = MockServiceConfig.DEFAULT_BUS,
    override val logger: Logger = MockServiceConfig.DEFAULT_LOGGER,
    override val scope: CoroutineScope = MockServiceConfig.DEFAULT_SCOPE,
) : BitframeApiMockConfig by BitframeApiMockConfig(appId, cache, session, daoFactory, bus, logger, scope)