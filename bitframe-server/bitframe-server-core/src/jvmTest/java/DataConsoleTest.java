import bitframe.types.ProcessTypesKt;
import bitframe.types.TypeInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataConsoleTest {

    @Test
    public void should_pass() {
        assertEquals(1 + 1, 2);
    }

    @Test
    public void should_copy_from_java_records() {
        var h1 = new Teacher("Teach", "te@ch.er");
        var h2 = h1.copy("Teach");
        assertEquals(h1, h2);
    }

    @Test
    public void should_process_a_java_class() {
        TypeInfo info = ProcessTypesKt.processJavaClass(Human.class);
        System.out.println(info);
    }
}
