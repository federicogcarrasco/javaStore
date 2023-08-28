package product.packaged;

import org.example.product.packaged.PackagedProduct;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void discountForPackagedProductMoreThan20() {
        PackagedProduct packagedProduct = new PackagedProduct();
        assertThrows(IllegalArgumentException.class, () -> {
            packagedProduct.setDiscount(26);
        });
    }

    @Test
    void priceLessThanCostAfterDiscountForCleaningProduct() {
        PackagedProduct packagedProduct = new PackagedProduct();
        packagedProduct.setCost(85);
        packagedProduct.setPrice(100);
        assertThrows(IllegalArgumentException.class, () -> {
            packagedProduct.setDiscount(16);
        });
    }

    @Test
    void importedPackagedProductHasTaxOf10() {
        PackagedProduct packagedProduct = new PackagedProduct();
        packagedProduct.setCost(100);
        packagedProduct.setPrice(120);
        packagedProduct.setImported(true);
        assertEquals(packagedProduct.getSalePrice(), 132);
    }
}
