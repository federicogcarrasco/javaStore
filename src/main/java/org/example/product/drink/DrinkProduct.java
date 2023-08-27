package org.example.product.drink;

import org.example.product.Food;
import org.example.product.Product;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DrinkProduct extends Product implements Food {
    private boolean alcohol;
    private Integer alcoholPercentage;
    private boolean imported;
    private Date expirationDate;
    private short calories;

    @Override
    public void setId(String id) {
        Pattern pattern = Pattern.compile("^AC[0-9][0-9][0-9]$");
        Matcher matcher = pattern.matcher(id);
        if (matcher.find())
            super.setId(id);
        else
            throw new IllegalArgumentException("Wrong Id format, expected: ACXXX where X = digit");
    }

    public void setAlcohol(boolean alcohol) {
        if (alcohol && alcoholPercentage == null)
            throw new IllegalArgumentException("You have to set alcoholPercentage before setting this drink to alcoholic");
        this.alcohol = alcohol;
    }

    public Integer getAlcoholPercentage() {
        return this.alcohol ? alcoholPercentage : null;
    }

    // other Getters and Setters...

    public boolean isAlcohol() {
        return alcohol;
    }

    public void setAlcoholPercentage(Integer alcoholPercentage) {
        this.alcoholPercentage = alcoholPercentage;
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
