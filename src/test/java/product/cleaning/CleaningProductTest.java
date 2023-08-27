package product.cleaning;

import org.example.product.cleaning.CleaningProduct;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CleaningProductTest {

    @Test
    void createCleaningProductWithCorrectId() {
        CleaningProduct cleaningProduct = new CleaningProduct();
        assertDoesNotThrow(() -> {
            cleaningProduct.setId("AZ001");
        });
    }

    @Test
    void createCleaningProductWithWrongId() {
        CleaningProduct cleaningProduct = new CleaningProduct();
        assertThrows(IllegalArgumentException.class, () -> {
            cleaningProduct.setId("AX001");
        });
    }
}
