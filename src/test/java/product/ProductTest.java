package product;

import org.example.product.Product;
import org.example.product.packaged.Packaged;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTest {

    @Test
    void createProductWithToShortId() {
        Product product = new Packaged();
        assertThrows(IllegalArgumentException.class, () -> {
            product.setId("AB01");
        });
    }

    @Test
    void createProductWithToLongId() {
        Product product = new Packaged();
        assertThrows(IllegalArgumentException.class, () -> {
            product.setId("AB0011");
        });
    }
}
