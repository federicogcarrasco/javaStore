package org.example.product.cleaning;

import org.example.product.Product;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CleaningProduct extends Product {

    private UseType useType;

    @Override
    public void setId(String id) {
        Pattern pattern = Pattern.compile("^AZ[0-9][0-9][0-9]$");
        Matcher matcher = pattern.matcher(id);
        if (matcher.find())
            super.setId(id);
        else
            throw new IllegalArgumentException("Wrong Id format, expected: AZXXX where X = digit");
    }

    @Override
    public void setPrice(double price) {
        if (price > getCost() * 1.25)
            throw new IllegalArgumentException("Error with product: " + getId() + " " + getDescription() +
                    ", earning for a cleaning product cannot be more than 25%");

        if (price < getCost() * 1.10 &&
                getUseType() != UseType.CLOTHES &&
                getUseType() != UseType.MULTIPURPOSE)
            throw new IllegalArgumentException("Error with product " + getId() + " " + getDescription() +
                    ", earnings for a cleaning product that is not for clothes or multipurpose cannot be less than 10%");
        super.setPrice(price);
    }

    @Override
    public void setDiscount(float discount) {
        if (discount > 25 ||
            getSalePrice() * (100 - discount) / 100 < getCost())
            throw new IllegalArgumentException("Discount for product " + getId() + " could not be applied");
        super.setDiscount(discount);
    }

// other Getters and Setters...

    public UseType getUseType() {
        return useType;
    }

    public void setUseType(UseType useType) {
        this.useType = useType;
    }
}
