import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pimonitor.client.MonitorApi;
import pimonitor.client.PiMonitorApiBuilder;
import pimonitor.client.MonitorApiKtor;
import pimonitor.client.MonitorApiMock;

public class ApiInstantiationTest {
    @Test
    public void should_return_a_ktor_api() {
        MonitorApi api = new PiMonitorApiBuilder()
                .setAppId("TEST-APP")
                .setUrl("test-url")
                .build();
        Assertions.assertTrue(api instanceof MonitorApiKtor);
    }

    @Test
    public void should_return_a_mock_api() {
        MonitorApi api = new PiMonitorApiBuilder().build();
        Assertions.assertTrue(api instanceof MonitorApiMock);
    }

    @Test
    public void should_instantiate_ktor_api_with_callback_api() {
        MonitorApi api = PiMonitorApiBuilder.build(config -> {
            config.setAppId("Test-app");
            config.setUrl("Test url");
            config.setNamespace("test.namespace");
        });
        Assertions.assertTrue(api instanceof MonitorApiKtor);
    }

    @Test
    public void should_instantiate_mock_api_with_callback_api() {
        MonitorApi api = PiMonitorApiBuilder.build(config -> {
        });
        Assertions.assertTrue(api instanceof MonitorApiMock);
    }
}
