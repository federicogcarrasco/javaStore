package product.cleaning;

import org.example.product.cleaning.Cleaning;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CleaningTest {

    @Test
    void createCleaningWithCorrectId() {
        Cleaning cleaning = new Cleaning();
        assertDoesNotThrow(() -> {
            cleaning.setId("AZ001");
        });
    }

    @Test
    void createCleaningWithWrongId() {
        Cleaning cleaning = new Cleaning();
        assertThrows(IllegalArgumentException.class, () -> {
            cleaning.setId("AX001");
        });
    }
}
