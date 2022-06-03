package pimonitor.client

import akkounts.sage.SageOneZAService
import akkounts.sage.SageOneZAServiceMock
import bitframe.client.BitframeApiConfigMock
import bitframe.core.DaoFactory
import bitframe.core.Session
import cache.Cache
import events.EventBus
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json
import live.MutableLive
import logging.Logger
import mailer.Mailer
import pimonitor.core.configs.MonitorServiceConfigDaod
import pimonitor.core.picortex.PiCortexService
import pimonitor.core.picortex.PiCortexServiceMock

interface MonitorApiConfigMock : BitframeApiConfigMock, MonitorServiceConfigDaod {
    private class Default(
        override val appId: String = BitframeApiConfigMock.DEFAULT_APP_ID,
        override val cache: Cache = BitframeApiConfigMock.DEFAULT_CACHE,
        override val session: MutableLive<Session> = BitframeApiConfigMock.DEFAULT_LIVE_SESSION,
        override val daoFactory: DaoFactory = BitframeApiConfigMock.DEFAULT_DAO_FACTORY,
        override val bus: EventBus = BitframeApiConfigMock.DEFAULT_BUS,
        override val logger: Logger = BitframeApiConfigMock.DEFAULT_LOGGER,
        override val mailer: Mailer = BitframeApiConfigMock.DEFAULT_MAILER,
        override val json: Json = BitframeApiConfigMock.DEFAULT_JSON,
        override val scope: CoroutineScope = BitframeApiConfigMock.DEFAULT_SCOPE,
        override val sage: SageOneZAService = SageOneZAServiceMock(),
        override val picortex: PiCortexService = PiCortexServiceMock()
    ) : MonitorApiConfigMock

    companion object {
        operator fun invoke(
            appId: String = BitframeApiConfigMock.DEFAULT_APP_ID,
            cache: Cache = BitframeApiConfigMock.DEFAULT_CACHE,
            session: MutableLive<Session> = BitframeApiConfigMock.DEFAULT_LIVE_SESSION,
            daoFactory: DaoFactory = BitframeApiConfigMock.DEFAULT_DAO_FACTORY,
            bus: EventBus = BitframeApiConfigMock.DEFAULT_BUS,
            logger: Logger = BitframeApiConfigMock.DEFAULT_LOGGER,
            mailer: Mailer = BitframeApiConfigMock.DEFAULT_MAILER,
            json: Json = BitframeApiConfigMock.DEFAULT_JSON,
            scope: CoroutineScope = BitframeApiConfigMock.DEFAULT_SCOPE,
            sage: SageOneZAService = SageOneZAServiceMock(),
            picortex: PiCortexService = PiCortexServiceMock()
        ): MonitorApiConfigMock = Default(appId, cache, session, daoFactory, bus, logger, mailer, json, scope, sage, picortex)
    }
}