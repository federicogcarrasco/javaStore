package org.example.product.packaged;

import org.example.product.Food;
import org.example.product.Product;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PackagedProduct extends Product implements Food {
    private PackageType packageType;
    private boolean imported;
    private Date expirationDate;
    private short calories;

    @Override
    public double getSalePrice() {
        if (imported)
            return super.getSalePrice() * 1.10;
        return super.getSalePrice();
    }

    @Override
    public void setId(String id) {
        Pattern pattern = Pattern.compile("^AB[0-9][0-9][0-9]$");
        Matcher matcher = pattern.matcher(id);
        if (matcher.find())
            super.setId(id);
        else
            throw new IllegalArgumentException("Wrong Id format, expected: ABXXX where X = digit");
    }

    @Override
    public void setPrice(double price) {
        if (price > getCost() * 1.20)
            throw new IllegalArgumentException("Error with product: " + getId() + " " + getDescription() +
                    ", earning for food product cannot be more than 20%");
        super.setPrice(price);
    }

    @Override
    public void setDiscount(float discount) {
        if (discount > 20 ||
                getSalePrice() * (100 - discount) / 100 < getCost())
            throw new IllegalArgumentException("Discount for product " + getId() + " could not be applied");
        super.setDiscount(discount);
    }

// other Getters and Setters...

    public PackageType getPackageType() {
        return packageType;
    }

    public void setPackageType(PackageType packageType) {
        this.packageType = packageType;
    }

    public boolean isImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }

    @Override
    public Date getExpirationDate() {
        return expirationDate;
    }

    @Override
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public short getCalories() {
        return calories;
    }

    @Override
    public void setCalories(short calories) {
        this.calories = calories;
    }
}
