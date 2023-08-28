package org.example.product;

public abstract class Product implements AppliesDiscount {
    private String id;
    private String description;
    private int stock;
    private double price;
    private double cost;
    private boolean forSale;
    private float discount;

    @Override
    public double getSalePrice() {
        return price - price * discount / 100;
    }

    @Override
    public void setDiscount(float discount) {
        if (discount < 0)
            throw new ArithmeticException("Discount cannot be negative");
        if (discount > 100)
            throw new ArithmeticException("Discount cannot be bigger than a 100%");
        this.discount = discount;
    }

    // other Getters and Setters...

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public float getDiscount() {
        return discount;
    }
}
