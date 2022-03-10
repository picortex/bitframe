package intergration;

import payments.requests.*;
import kotlinx.serialization.Serializable;
import org.junit.jupiter.api.Test;

import static expect.Builders.expect;

public class SerializationTest {
    @Serializable
    record User(String name) {
    }

    @Test
    public void should_serialize_a_class() {
        var index = 1;
        var name = "John Doe " + index;
        var user = new User(name);
        expect(user.name).toBe(name);
        var agency = new TaxAgency("Tanzania Revenue Authority");
        var tax = new Tax("me", 4, agency);
    }
}
