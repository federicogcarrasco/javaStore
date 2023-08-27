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

    // other Getters and Setters...

    public UseType getUseType() {
        return useType;
    }

    public void setUseType(UseType useType) {
        this.useType = useType;
    }
}
