package bitframe.api

import bitframe.ApiConfigMock
import bitframe.DaoFactory
import bitframe.Session
import cache.Cache
import events.EventBus
import koncurrent.Executor
import kotlinx.serialization.StringFormat
import live.MutableLive
import logging.Logger
import mailer.Mailer

class ApiConfigMockImpl(
    override val appId: String,
    override val session: MutableLive<Session>,
    override val cache: Cache,
    override val bus: EventBus,
    override val logger: Logger,
    override val executor: Executor,
    override val codec: StringFormat,
    override val database: DaoFactory,
    override val mailer: Mailer
) : ApiConfigMock