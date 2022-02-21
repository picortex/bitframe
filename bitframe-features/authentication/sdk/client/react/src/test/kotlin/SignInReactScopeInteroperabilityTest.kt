import bitframe.client.UIScopeConfig
import bitframe.client.signin.SignInReactScope
import bitframe.client.signin.SignInServiceMock
import expect.expect
import kotlin.test.Test

class SignInReactScopeInteroperabilityTest {
    val service = SignInServiceMock()

    init {
        globalThis.scope = SignInReactScope(UIScopeConfig(service))
    }

    @Test
    fun should_be_able_to_access_use_scope_state_from_javascript() {
        val scope = globalThis.scope
        expect(scope.useScopeState == undefined).toBe(false)
    }
}