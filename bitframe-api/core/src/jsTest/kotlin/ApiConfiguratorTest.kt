import bitframe.client.configurators.ApiConfigurator
import bitframe.client.configurators.ApiConfiguratorImpl
import bitframe.client.globalThis
import bitframe.client.jso
import expect.expect
import kotlin.test.Test

class ApiConfiguratorTest {
    @Test
    fun should_be_able_to_get_app_id_from_raw_json_object() {
        val obj = jso<dynamic> {
            appId = "test-id"
        }.unsafeCast<ApiConfigurator>()
        expect(obj.appId).toBe("test-id")
    }

    @Test
    fun can_read_exported_interfaces() {
        globalThis.conf = ApiConfiguratorImpl().apply {
            appId = "test-id"
            logging {
                console = true
            }
        }
        val appId = globalThis.conf.appId.unsafeCast<String?>()
        val cnsl = globalThis.conf.logging.console.unsafeCast<Boolean>()
        expect(appId).toBe("test-id")
        expect(cnsl).toBe(true)
    }
}