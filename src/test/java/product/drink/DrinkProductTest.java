package product.drink;

import org.example.product.drink.DrinkProduct;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DrinkProductTest {

    @Test
    void createDrinkProductWithCorrectId() {
        DrinkProduct drink = new DrinkProduct();
        assertDoesNotThrow(() -> {
            drink.setId("AC001");
        });
    }

    @Test
    void createDrinkProductWithWrongId() {
        DrinkProduct drink = new DrinkProduct();
        assertThrows(IllegalArgumentException.class, () -> {
            drink.setId("AX001");
        });
    }

    @Test
    void createAlcoholicDrinkProductWithAlcoholPercentage() {
        DrinkProduct drink = new DrinkProduct();
        drink.setAlcoholPercentage(5);
        assertDoesNotThrow(() -> {
            drink.setAlcohol(true);
        });
    }

    @Test
    void createAlcoholicDrinkProductWithoutAlcoholPercentage() {
        DrinkProduct drink = new DrinkProduct();
        assertThrows(IllegalArgumentException.class, () -> {
            drink.setAlcohol(true);
        });
    }

    @Test
    void getAlcoholPercentageFromAlcoholicDrinkProduct() {
        DrinkProduct drinkProduct = new DrinkProduct();
        drinkProduct.setAlcoholPercentage(5);
        drinkProduct.setAlcohol(true);
        assertEquals(drinkProduct.getAlcoholPercentage(), 5);
    }

    @Test
    void getAlcoholPercentageFromNonAlcoholicDrinkProduct() {
        DrinkProduct drinkProduct = new DrinkProduct();
        drinkProduct.setAlcoholPercentage(5);
        assertNull(drinkProduct.getAlcoholPercentage());
    }
}
