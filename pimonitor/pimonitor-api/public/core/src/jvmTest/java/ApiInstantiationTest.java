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
}
