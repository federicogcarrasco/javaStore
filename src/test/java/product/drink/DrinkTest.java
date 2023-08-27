package product.drink;

import org.example.product.drink.Drink;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DrinkTest {

    @Test
    void createDrinkWithCorrectId() {
        Drink drink = new Drink();
        assertDoesNotThrow(() -> {
            drink.setId("AC001");
        });
    }

    @Test
    void createDrinkWithWrongId() {
        Drink drink = new Drink();
        assertThrows(IllegalArgumentException.class, () -> {
            drink.setId("AX001");
        });
    }

    @Test
    void createAlcoholicDrinkWithAlcoholPercentage() {
        Drink drink = new Drink();
        drink.setAlcoholPercentage(5);
        assertDoesNotThrow(() -> {
            drink.setAlcohol(true);
        });
    }

    @Test
    void createAlcoholicDrinkWithoutAlcoholPercentage() {
        Drink drink = new Drink();
        assertThrows(IllegalArgumentException.class, () -> {
            drink.setAlcohol(true);
        });
    }

    @Test
    void getAlcoholPercentageFromAlcoholicDrink() {
        Drink drink = new Drink();
        drink.setAlcoholPercentage(5);
        drink.setAlcohol(true);
        assertEquals(drink.getAlcoholPercentage(), 5);
    }

    @Test
    void getAlcoholPercentageFromNonAlcoholicDrink() {
        Drink drink = new Drink();
        drink.setAlcoholPercentage(5);
        assertNull(drink.getAlcoholPercentage());
    }
}
