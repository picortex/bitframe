package bitframe

import bitframe.internal.ApiConfigMockImpl
import cache.Cache
import events.EventBus
import koncurrent.Executor
import kotlinx.serialization.StringFormat
import live.MutableLive
import logging.Logger
import mailer.Mailer

val API_CONFIG_MOCK_DEFAULT: ApiConfigMock = ApiConfigMockImpl

@PublishedApi
internal val DEFAULT = API_CONFIG_MOCK_DEFAULT

inline fun ApiConfigMock(
    appId: String = DEFAULT.appId,
    cache: Cache = DEFAULT.cache,
    bus: EventBus = DEFAULT.bus,
    session: MutableLive<Session> = DEFAULT.session,
    logger: Logger = DEFAULT.logger,
    executor: Executor = DEFAULT.executor,
    codec: StringFormat = DEFAULT.codec,
    database: DaoFactory = DEFAULT.database,
    mailer: Mailer = DEFAULT.mailer
): ApiConfigMock = ApiConfigMockImpl(appId, session, cache, bus, logger, executor, database, codec, mailer)