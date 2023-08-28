package product;

import org.example.product.Product;
import org.example.product.packaged.PackagedProduct;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class AppliesDiscountTest {

    @Test
    void applyDiscount() {
        Product product = new PackagedProduct();
        assertDoesNotThrow(() -> {
            product.setDiscount(10);
        });
    }

    @Test
    void applyNegativeDiscount() {
        Product product = new PackagedProduct();
        assertThrows(ArithmeticException.class, () -> {
            product.setDiscount(-10);
        });
    }

    @Test
    void applyMoreThanA100Discount() {
        Product product = new PackagedProduct();
        assertThrows(ArithmeticException.class, () -> {
            product.setDiscount(101);
        });
    }

    @Test
    void getSalePrice() {
        Product product = new PackagedProduct();
        product.setPrice(100);
        product.setDiscount(15);
        assertEquals(product.getSalePrice(), 85);
    }
}
