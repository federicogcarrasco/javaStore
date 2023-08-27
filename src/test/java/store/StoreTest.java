package store;

import org.example.product.Product;
import org.example.product.packaged.PackagedProduct;
import org.example.store.Store;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StoreTest {
    private static Store store;
    private final int STARTING_BALANCE = 10_000;

    @BeforeAll
    static void setup() {
        store = new Store();
        store.setMaxAmountStockProducts(1000);
    }

    @BeforeEach
    void init() {
        store.setCashBalance(STARTING_BALANCE);
    }

    @Test
    void purchasedProductToCorrectList() {
        Product product = new PackagedProduct();
        product.setId("AB001");
        store.buy(product);
        assertTrue(store.getStock().get('B').contains(product));
    }

    @Test
    void purchasedProductForSaleByDefault() {
        Product product = new PackagedProduct();
        product.setId("AB001");
        product.setForSale(false);
        store.buy(product);
        assertTrue(product.isForSale());
    }

    @Test
    void purchasedProductUpdatesCashBalance() {
        Product product = new PackagedProduct();
        product.setId("AB001");
        product.setCost(1_000);
        product.setStock(1);
        store.buy(product);
        assertEquals(
                store.getCashBalance(),
                STARTING_BALANCE - product.getCost() * product.getStock());
    }

    @Test
    void notEnoughCashBalanceToBuyProduct() {
        Product product = new PackagedProduct();
        product.setId("AB001");
        product.setCost(100_000);
        product.setStock(1);
        assertThrows(Exception.class, () -> {
            store.buy(product);
        });
    }

    @Test
    void exceededAmountOfMaxProducts() {
        Product product = new PackagedProduct();
        product.setId("AB001");
        product.setStock(10_000);
        assertThrows(Exception.class, () -> {
            store.buy(product);
        });
    }
}
