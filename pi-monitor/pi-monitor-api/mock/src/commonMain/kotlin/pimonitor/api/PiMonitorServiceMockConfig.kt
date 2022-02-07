package pimonitor.api

import bitframe.api.BitframeServiceConfig
import bitframe.api.BitframeServiceMockConfig
import bitframe.actors.users.User
import cache.Cache
import cache.MockCache
import events.EventBus
import events.InMemoryEventBus
import kotlinx.collections.interoperable.mutableListOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import live.MutableLive
import logging.ConsoleAppender
import logging.Logger
import pimonitor.client.evaluation.businesses.BusinessesServiceConfig
import pimonitor.client.monitors.MonitorsServiceConfig
import kotlin.jvm.JvmOverloads
import bitframe.authentication.signin.Session as SignInSession
import pimonitor.client.monitors.Session as MonitorSession

class PiMonitorServiceMockConfig @JvmOverloads constructor(
    override val signInSession: MutableLive<SignInSession> = MutableLive(SignInSession.Unknown),
    override val monitorSession: MutableLive<MonitorSession> = MutableLive(MonitorSession.Unknown),
    override val appId: String = "mock-service",
    override val cache: Cache = MockCache(),
    override val bus: EventBus = InMemoryEventBus(),
    override val logger: Logger = Logger(ConsoleAppender()),
    override val scope: CoroutineScope = CoroutineScope(SupervisorJob()),
    override val users: MutableList<User> = mutableListOf()
) : BitframeServiceConfig, BitframeServiceMockConfig, MonitorsServiceConfig, BusinessesServiceConfig