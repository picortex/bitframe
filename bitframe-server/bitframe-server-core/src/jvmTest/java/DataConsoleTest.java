import bitframe.types.ProcessTypesKt;
import bitframe.types.TypeInfo;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataConsoleTest {

    @Test
    public void should_pass() {
        assertEquals(1 + 1, 2);
    }

    @Test
    public void should_process_a_java_class() {
        TypeInfo info = ProcessTypesKt.processJavaClass(Human.class);
        System.out.println(info);
    }
}
