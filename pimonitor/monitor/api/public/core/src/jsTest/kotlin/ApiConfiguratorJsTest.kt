import bitframe.client.configurators.ApiConfigurator
import bitframe.client.jso
import expect.expect
import expect.toBe
import pimonitor.client.MonitorApiKtor
import pimonitor.client.MonitorApiMock
import kotlin.test.Test
import kotlin.js.console as consl

class ApiConfiguratorJsTest {

    init {
        consl.log("teting")
    }

    @Test
    fun should_have_an_easy_syntax_for_creating_a_pimonitor_api_instance() {
        val api = api(jso<dynamic> {
            appId = "TEST_APP_ID"
            url = "https://dev.picortex.com"
            logging = jso {
                console = true
                sentry = true
            }
        }.unsafeCast<ApiConfigurator>())
        expect(api).toBeNonNull()
    }

    @Test
    fun should_return_a_mock_api_when_appId_or_url_is_not_set() {
        val api = api(jso<ApiConfigurator> { })
        expect(api).toBe<MonitorApiMock>()
    }

    @Test
    fun should_return_a_ktor_api_when_an_appId_and_a_url_is_set() {
        val api = api(jso<ApiConfigurator> {
            appId = "TEST_APP_ID"
            url = "https://dev.pimonitor.com"
        })
        expect(api).toBe<MonitorApiKtor>()
    }

    @Test
    fun should_have_a_default_console_logger() {
        val api = api(jso<dynamic> {
            logging = jso { console = true }
        }.unsafeCast<ApiConfigurator>())
        api.config.logger.info("test logging")
    }

    @Test
    fun should_be_able_to_turn_logging_off() {
        val api = api(jso<dynamic> {
            logging = jso { console = false }
        }.unsafeCast<ApiConfigurator>())
        api.config.logger.info("test logging")
    }
}