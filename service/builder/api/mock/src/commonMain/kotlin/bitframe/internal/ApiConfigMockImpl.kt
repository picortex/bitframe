package bitframe.internal

import bitframe.API_CONFIG_DEFAULT
import bitframe.ApiConfigMock
import bitframe.DaoFactory
import bitframe.SERVICE_CONFIG_DAO_DEFAULT
import bitframe.Session
import cache.Cache
import events.EventBus
import koncurrent.Executor
import koncurrent.SynchronousExecutor
import kotlinx.serialization.StringFormat
import cinematic.MutableLive
import logging.Logger
import mailer.Mailer

@PublishedApi
internal class ApiConfigMockImpl(
    override val appId: String,
    override val session: MutableLive<Session>,
    override val cache: Cache,
    override val bus: EventBus,
    override val logger: Logger,
    override val executor: Executor,
    override val database: DaoFactory,
    override val codec: StringFormat,
    override val mailer: Mailer
) : ApiConfigMock {
    companion object Default : ApiConfigMock by ApiConfigMockImpl(
        appId = API_CONFIG_DEFAULT.appId,
        session = API_CONFIG_DEFAULT.session,
        cache = API_CONFIG_DEFAULT.cache,
        bus = SERVICE_CONFIG_DAO_DEFAULT.bus,
        logger = SERVICE_CONFIG_DAO_DEFAULT.logger,
        executor = SynchronousExecutor,
        database = SERVICE_CONFIG_DAO_DEFAULT.database,
        codec = SERVICE_CONFIG_DAO_DEFAULT.codec,
        mailer = SERVICE_CONFIG_DAO_DEFAULT.mailer
    )
}