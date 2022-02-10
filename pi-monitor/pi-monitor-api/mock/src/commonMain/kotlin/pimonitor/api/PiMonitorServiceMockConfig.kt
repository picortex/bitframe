package pimonitor.api

import bitframe.api.BitframeServiceMockConfig
import bitframe.daos.DaoFactory
import bitframe.service.Session
import bitframe.service.client.config.MockServiceConfig
import cache.Cache
import events.EventBus
import kotlinx.coroutines.CoroutineScope
import live.MutableLive
import logging.Logger
import kotlin.jvm.JvmOverloads

class PiMonitorServiceMockConfig @JvmOverloads constructor(
    override val appId: String = MockServiceConfig.DEFAULT_APP_ID,
    override val cache: Cache = MockServiceConfig.DEFAULT_CACHE,
    override val session: MutableLive<Session> = MockServiceConfig.DEFAULT_LIVE_SESSION,
    override val daoFactory: DaoFactory = MockServiceConfig.DEFAULT_DAO_FACTORY,
    override val bus: EventBus = MockServiceConfig.DEFAULT_BUS,
    override val logger: Logger = MockServiceConfig.DEFAULT_LOGGER,
    override val scope: CoroutineScope = MockServiceConfig.DEFAULT_SCOPE,
    override val monitorSession: MutableLive<MonitorSession> = MutableLive(MonitorSession.Unknown)
) : BitframeServiceMockConfig by BitframeServiceMockConfig(appId, cache, session, daoFactory, bus, logger, scope), MonitorsServiceConfig, BusinessesServiceConfig