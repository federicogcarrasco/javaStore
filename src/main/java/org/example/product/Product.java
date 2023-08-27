package org.example.product;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Product {
    private String id;
    private String description;
    private int stock;
    private double price;
    private double cost;
    private boolean forSale;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        Pattern pattern = Pattern.compile("^A[BCZ]\\d\\d\\d$");
        Matcher matcher = pattern.matcher(id);
        if(matcher.find()) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("Wrong Id format.");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean isForSale() {
        return forSale;
    }

    public void setForSale(boolean forSale) {
        this.forSale = forSale;
    }
}
