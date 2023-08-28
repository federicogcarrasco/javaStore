package store;

import org.example.product.Product;
import org.example.product.drink.DrinkProduct;
import org.example.product.packaged.PackagedProduct;
import org.example.store.Store;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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

    @Test
    void saleOfMoreThan3Products() {
        assertThrows(Exception.class, () -> {
            store.sale(Map.ofEntries(
                            Map.entry("AB001", (byte) 1),
                            Map.entry("AB002", (byte) 1),
                            Map.entry("AB003", (byte) 1),
                            Map.entry("AB004", (byte) 1)
                    ));
        });
    }

    @Test
    void saleOfMoreThan10Units() {
        assertThrows(Exception.class, () -> {
           store.sale(Map.ofEntries(
                            Map.entry("AB001", (byte) 11)
           ));
        });
    }

    @Test
    void saleOfProducts() {
        Product product1 = new PackagedProduct();
        product1.setId("AB001");
        product1.setPrice(100);
        product1.setStock(1);
        product1.setDescription("Kg of chicken");
        store.buy(product1);

        Product product2 = new DrinkProduct();
        product2.setId("AC001");
        product2.setPrice(50);
        product2.setStock(3);
        product2.setDescription("Water");
        store.buy(product2);

        System.out.println("Cash balance before: " + store.getCashBalance());
        System.out.println();

        assertDoesNotThrow(() -> {
            store.sale(Map.ofEntries(
                Map.entry("AB001", (byte) 1),
                Map.entry("AC001", (byte) 3)
            ));
        });

        System.out.println();
        System.out.println("Cash balance after: " + store.getCashBalance());
    }

    @Test
    void saleOfMoreProductsThanStock() {
        Product product1 = new PackagedProduct();
        product1.setId("AB001");
        product1.setPrice(100);
        product1.setStock(1);
        product1.setDescription("Kg of chicken");
        store.buy(product1);
        store.sale(new HashMap<>(Map.ofEntries(
                Map.entry("AB001", (byte) 2)
        )));
        System.out.println();
        System.out.println("Product forSale: " + product1.isForSale());
    }

    @Test
    void saleOfSameAmountProductsAndStock() {
        Product product1 = new PackagedProduct();
        product1.setId("AB001");
        product1.setPrice(100);
        product1.setStock(1);
        product1.setDescription("Kg of chicken");
        store.buy(product1);
        store.sale(new HashMap<>(Map.ofEntries(
                Map.entry("AB001", (byte) 1)
        )));
        System.out.println();
        System.out.println("Product forSale: " + product1.isForSale());
    }

    @Test
    void saleOfProductNotForSale() {
        Product product1 = new PackagedProduct();
        product1.setId("AB001");
        product1.setPrice(100);
        product1.setStock(1);
        product1.setDescription("Kg of chicken");
        store.buy(product1);

        Product product2 = new DrinkProduct();
        product2.setId("AC001");
        product2.setPrice(50);
        product2.setStock(3);
        product2.setDescription("Water");
        store.buy(product2);

        System.out.println("Sale to make product onSale false");
        store.sale(new HashMap<>(Map.ofEntries(
                Map.entry("AB001", (byte) 1)
        )));

        System.out.println();

        System.out.println("Actual sale to check");
        store.sale(new HashMap<>(Map.ofEntries(
                Map.entry("AB001", (byte) 1),
                Map.entry("AC001", (byte) 3)
        )));
    }
}
