package bitframe

import bitframe.internal.ApiConfigMockImpl
import keep.Cache
import events.EventBus
import koncurrent.Executor
import kotlinx.serialization.StringFormat
import cinematic.MutableLive
import lexi.Logger
import mailer.Mailer

val API_CONFIG_MOCK_DEFAULT: ApiConfigMock = ApiConfigMockImpl

inline fun ApiConfigMock(
    appId: String = API_CONFIG_MOCK_DEFAULT.appId,
    cache: Cache = API_CONFIG_MOCK_DEFAULT.cache,
    bus: EventBus = API_CONFIG_MOCK_DEFAULT.bus,
    session: MutableLive<Session> = API_CONFIG_MOCK_DEFAULT.session,
    logger: Logger = API_CONFIG_MOCK_DEFAULT.logger,
    executor: Executor = API_CONFIG_MOCK_DEFAULT.executor,
    codec: StringFormat = API_CONFIG_MOCK_DEFAULT.codec,
    database: DaoFactory = API_CONFIG_MOCK_DEFAULT.database,
    mailer: Mailer = API_CONFIG_MOCK_DEFAULT.mailer
): ApiConfigMock = ApiConfigMockImpl(appId, session, cache, bus, logger, executor, database, codec, mailer)