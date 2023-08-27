package org.example.store;

import org.example.product.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Store {
    private String name;
    private int maxAmountStockProducts;
    private int amountStockProducts = 0;
    private double cashBalance;
    private final Map<Character, List<Product>> stock = Map.ofEntries(
            Map.entry('B', new ArrayList<>()),
            Map.entry('C', new ArrayList<>()),
            Map.entry('Z', new ArrayList<>())
    );

    public void buy(Product product) {
        try {
            if (cashBalance - ( product.getStock() * product.getCost() ) < 0)
                throw new Exception("Not enough cash to make the buy.");
            if (amountStockProducts + product.getStock() > maxAmountStockProducts)
                throw new Exception("The buy exceed the maximum amount of products allowed.");

            Product productOldData = searchInStock(product.getId());
            if (productOldData != null)
                product.setStock(product.getStock() + productOldData.getStock());

            product.setForSale(true);
            getProductList(product.getId()).add(product);
            amountStockProducts += product.getStock();
            cashBalance -= product.getCost() * product.getStock();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Product searchInStock(String id) {
        List<Product> stockList = getProductList(id);
        for (Product product : stockList) {
            if (product.getId().equals(id))
                return product;
        }
        return null;
    }

    private List<Product> getProductList(String id) {
        return stock.get(id.charAt(1));
    }

    // other Getters and Setters...

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxAmountStockProducts() {
        return maxAmountStockProducts;
    }

    public void setMaxAmountStockProducts(int maxAmountStockProducts) {
        this.maxAmountStockProducts = maxAmountStockProducts;
    }

    public int getAmountStockProducts() {
        return amountStockProducts;
    }

    public void setAmountStockProducts(int amountStockProducts) {
        this.amountStockProducts = amountStockProducts;
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(double cashBalance) {
        this.cashBalance = cashBalance;
    }

    public Map<Character, List<Product>> getStock() {
        return stock;
    }
}
