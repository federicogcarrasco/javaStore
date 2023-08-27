package product.packaged;

import org.example.product.packaged.Packaged;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PackagedTest {

    @Test
    void createPackagedWithCorrectId() {
        Packaged packaged = new Packaged();
        assertDoesNotThrow(() -> {
            packaged.setId("AB001");
        });
    }

    @Test
    void createPackagedWithWrongId() {
        Packaged packaged = new Packaged();
        assertThrows(IllegalArgumentException.class, () -> {
            packaged.setId("AX001");
        });
    }
}
