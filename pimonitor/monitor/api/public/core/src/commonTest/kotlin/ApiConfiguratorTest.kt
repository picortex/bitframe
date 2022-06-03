import expect.expect
import expect.toBe
import pimonitor.client.MonitorApiKtor
import pimonitor.client.MonitorApiMock
import pimonitor.client.api
import kotlin.test.Test

class ApiConfiguratorTest {

    @Test
    fun should_have_an_easy_syntax_for_creating_a_pimonitor_api_instance() {
        val api = api {
            appId = "TEST_APP_ID"
            url = "https://dev.picortex.com"
            logging {
                console = true
                sentry = true
            }
        }
        expect(api).toBeNonNull()
    }

    @Test
    fun should_return_a_mock_api_when_appId_or_url_is_not_set() {
        val api = api { }
        expect(api).toBe<MonitorApiMock>()
    }

    @Test
    fun should_return_a_ktor_api_when_an_appId_and_a_url_is_set() {
        val api = api {
            appId = "TEST_APP_ID"
            url = "https://dev.pimonitor.com"
        }
        expect(api).toBe<MonitorApiKtor>()
    }

    @Test
    fun should_have_a_default_console_logger() {
        val api = api {
            logging { console = true }
        }
        api.config.logger.info("test logging")
    }

    @Test
    fun should_be_able_to_turn_logging_off() {
        val api = api {
            logging { console = false }
        }
        api.config.logger.info("test logging")
    }
}