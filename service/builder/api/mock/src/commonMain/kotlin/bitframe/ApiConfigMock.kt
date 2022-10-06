package bitframe

import bitframe.api.ApiConfigMockImpl
import cache.Cache
import cache.CacheMock
import events.EventBus
import koncurrent.Executor
import koncurrent.SynchronousExecutor
import kotlinx.serialization.StringFormat
import live.mutableLiveOf
import logging.Logger
import mailer.Mailer
import mailer.MockMailer
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmName

interface ApiConfigMock : ApiConfig, ServiceConfigDaod {
    companion object {
        @JvmStatic
        @JvmOverloads
        @JvmName("create")
        operator fun invoke(
            appId: String = "mock-app",
            cache: Cache = CacheMock(),
            bus: EventBus = ApiConfig.DEFAULT_BUS,
            logger: Logger = ApiConfig.DEFAULT_LOGGER,
            executor: Executor = SynchronousExecutor,
            codec: StringFormat = ServiceConfigDaod.DEFAULT_CODEC,
            database: DaoFactory = DaoFactoryMock(),
            mailer: Mailer = MockMailer()
        ): ApiConfigMock = ApiConfigMockImpl(appId, mutableLiveOf(Session.Unknown), cache, bus, logger, executor, codec, database, mailer)
    }
}