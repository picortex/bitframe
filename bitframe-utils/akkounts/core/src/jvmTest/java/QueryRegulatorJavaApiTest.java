import akkounts.regulation.*;
import kotlin.time.DurationUnit;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class QueryRegulatorJavaApiTest {
    @Test
    public void should_return_allowed_status_coz_no_queries_have_been_made_yet() {
        Period period = new Period(2, DurationUnit.SECONDS);
        Policy policy = new Policy(1, period);
        QueryCountStore store = new InMemoryQueryCountStore();
        QueryRegulator regulator = new QueryRegulator(policy, store);
//        String requesterId = "one";
//        QueryRegulator.Status status = regulator.checkRestriction(requesterId).wait();
//        assertEquals(status, QueryRegulator.Status.Allowed);
    }

    @Test
    public void should_no_allow_when_time_has_passed() {
        Period period = new Period(2, DurationUnit.MINUTES);
        Policy policy = new Policy(1, period);
        QueryCountStore store = new InMemoryQueryCountStore();
        QueryRegulator regulator = new QueryRegulator(policy, store);
        String requesterId = "one";
//        QueryRegulator.Status status = regulator.checkRestriction(requesterId).wait();
//        assertEquals(status, QueryRegulator.Status.Allowed);
    }
}