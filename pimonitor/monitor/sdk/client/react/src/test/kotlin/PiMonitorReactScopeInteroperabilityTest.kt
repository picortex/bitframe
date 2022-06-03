import bitframe.client.BitframeAppScopeConfig
import bitframe.client.SessionAware
import bitframe.client.globalThis
import expect.expect
import pimonitor.client.MonitorApi
import pimonitor.client.MonitorApiTest
import pimonitor.client.PiMonitorReactAppScope
import kotlin.test.Test

class PiMonitorReactScopeInteroperabilityTest {

    init {
        globalThis.scope = PiMonitorReactAppScope(BitframeAppScopeConfig(MonitorApiTest()))
    }

    val scope get() = globalThis.scope

    @Test
    fun should_have_a_non_null_session_object() {
        val session = scope.session.unsafeCast<SessionAware>()
        expect(session).toBeNonNull()
    }

    @Test
    fun should_have_a_non_null_api_object() {
        val api = scope.api.unsafeCast<MonitorApi>()
        expect(api).toBeNonNull()
    }

    @Test
    fun should_have_a_session_object_inside_the_api_object() {
        val session = scope.api.session.unsafeCast<SessionAware>()
        expect(session).toBeNonNull()
    }
}