package product.packaged;

import org.example.product.packaged.PackagedProduct;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PackagedProductTest {

    @Test
    void createPackagedProductWithCorrectId() {
        PackagedProduct packagedProduct = new PackagedProduct();
        assertDoesNotThrow(() -> {
            packagedProduct.setId("AB001");
        });
    }

    @Test
    void createPackagedProductWithWrongId() {
        PackagedProduct packagedProduct = new PackagedProduct();
        assertThrows(IllegalArgumentException.class, () -> {
            packagedProduct.setId("AX001");
        });
    }
}
