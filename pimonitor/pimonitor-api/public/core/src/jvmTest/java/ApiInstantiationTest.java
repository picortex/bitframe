import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pimonitor.client.PiMonitorApi;
import pimonitor.client.PiMonitorApiBuilder;
import pimonitor.client.PiMonitorApiKtor;
import pimonitor.client.PiMonitorApiMock;

public class ApiInstantiationTest {
    @Test
    public void should_return_a_ktor_api() {
        PiMonitorApi api = new PiMonitorApiBuilder()
                .setAppId("TEST-APP")
                .setUrl("test-url")
                .build();
        Assertions.assertTrue(api instanceof PiMonitorApiKtor);
    }

    @Test
    public void should_return_a_mock_api() {
        PiMonitorApi api = new PiMonitorApiBuilder().build();
        Assertions.assertTrue(api instanceof PiMonitorApiMock);
    }

    @Test
    public void should_instantiate_ktor_api_with_callback_api() {
        PiMonitorApi api = PiMonitorApiBuilder.build(config -> {
            config.setAppId("Test-app");
            config.setUrl("Test url");
            config.setNamespace("test.namespace");
        });
        Assertions.assertTrue(api instanceof PiMonitorApiKtor);
    }

    @Test
    public void should_instantiate_mock_api_with_callback_api() {
        PiMonitorApi api = PiMonitorApiBuilder.build(config -> {
        });
        Assertions.assertTrue(api instanceof PiMonitorApiMock);
    }
}
