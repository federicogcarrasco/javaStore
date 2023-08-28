package product;

import org.example.product.drink.DrinkProduct;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FoodTest {

    @Test
    void earningForFoodProductMoreThan20() {
        DrinkProduct product = new DrinkProduct();
        product.setCost(100);
        assertThrows(IllegalArgumentException.class, () -> {
            product.setPrice(121);
        });
    }
}
