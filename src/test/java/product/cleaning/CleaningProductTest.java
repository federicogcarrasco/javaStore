package product.cleaning;

import org.example.product.cleaning.CleaningProduct;
import org.example.product.cleaning.UseType;
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

    @Test
    void earningForCleaningProductMoreThan25() {
        CleaningProduct cleaningProduct = new CleaningProduct();
        cleaningProduct.setCost(100);
        assertThrows(IllegalArgumentException.class, () -> {
            cleaningProduct.setPrice(126);
        });
    }

    @Test
    void earningForCleaningProductLessThan10() {
        CleaningProduct cleaningProduct = new CleaningProduct();
        cleaningProduct.setCost(100);
        cleaningProduct.setUseType(UseType.FLOOR);
        assertThrows(IllegalArgumentException.class, () -> {
            cleaningProduct.setPrice(109);
        });
    }

    @Test
    void discountForCleaningProductMoreThan25() {
        CleaningProduct cleaningProduct = new CleaningProduct();
        assertThrows(IllegalArgumentException.class, () -> {
            cleaningProduct.setDiscount(26);
        });
    }

    @Test
    void priceLessThanCostAfterDiscountForCleaningProduct() {
        CleaningProduct cleaningProduct = new CleaningProduct();
        cleaningProduct.setCost(85);
        cleaningProduct.setPrice(100);
        assertThrows(IllegalArgumentException.class, () -> {
            cleaningProduct.setDiscount(16);
        });
    }
}
