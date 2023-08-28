package org.example.store;

import org.example.product.Product;
import org.example.product.drink.DrinkProduct;
import org.example.product.packaged.PackagedProduct;

import java.util.*;

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
        if (cashBalance - ( product.getStock() * product.getCost() ) < 0)
            throw new IllegalArgumentException("Not enough cash to make the buy.");
        if (amountStockProducts + product.getStock() > maxAmountStockProducts)
            throw new IllegalArgumentException("The buy exceed the maximum amount of products allowed.");

        Product productOldData = searchInStock(product.getId());
        if (productOldData != null)
            product.setStock(product.getStock() + productOldData.getStock());

        product.setForSale(true);
        getProductList(product.getId()).add(product);
        amountStockProducts += product.getStock();
        cashBalance -= product.getCost() * product.getStock();
    }

    public void sale(Map<String, Byte> saleData) {
        if (saleData.size() > 3)
            throw new IllegalArgumentException("No more than 3 products per purchase are allowed.");
        boolean moreThan10Units = false;
        for (Byte units : saleData.values())
            if (units > 10) {
                moreThan10Units = true;
                break;
            }
        if (moreThan10Units)
            throw new IllegalArgumentException("No more than 10 units per product are allowed.");

        double totalValue = 0;
        for (String productId : saleData.keySet()) {
            Product product = searchInStock(productId);

            if (!product.isForSale()) {
                System.out.println("The product: " + productId + " " + product.getDescription() + " is not on sale.");
                System.out.println();
                saleData.remove(productId);
                continue;
            }

            if (product.getStock() < saleData.get(productId)) {
                System.out.println("There is less stock of the product than is needed.");
                saleData.put(productId, (byte) product.getStock());
                product.setStock(0);
                product.setForSale(false);
            } else if (product.getStock() == saleData.get(productId)) {
                product.setStock(0);
                product.setForSale(false);
            } else {
                product.setStock(product.getStock() - saleData.get(productId));
            }

            System.out.println(productId + " " + product.getDescription() + " " +
                    saleData.get(productId) + " X " + product.getSalePrice());
            System.out.println();
            totalValue += product.getSalePrice() * saleData.get(productId);
        }

        cashBalance += totalValue;
        System.out.println("TOTAL SALE: " + totalValue);
    }

    public List<String> getFoodWithLessDiscount(int discount) {
        List<String> result = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        products.addAll(getProductList("xB"));
        products.addAll(getProductList("xC"));
        products.stream().filter(product -> (product.getDiscount() < discount && (
                                            (product instanceof DrinkProduct && !((DrinkProduct) product).isImported()) ||
                                            (product instanceof PackagedProduct && !((PackagedProduct) product).isImported()))))
                            .sorted(Comparator.comparing(Product::getSalePrice))
                            .forEach(product -> result.add(product.getDescription().toUpperCase()));
        return result;
    }

    public void listProductsWithLessEarningPercentage(int percentage) {
        List<List<String>> results = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        products.addAll(getProductList("xB"));
        products.addAll(getProductList("xC"));
        products.addAll(getProductList("xZ"));
        products.stream()
                .filter(product -> product.getPrice() < product.getCost() * percentage / 100)
                .forEach(product -> results.add(new ArrayList<>(Arrays.asList(product.getId(),
                                                                                product.getDescription(),
                                                                                String.valueOf(product.getStock())))));
        results.forEach(r -> System.out.println(r.get(0) + " " + r.get(1) + " " + r.get(2)));
    }

    public Product searchInStock(String id) {
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
