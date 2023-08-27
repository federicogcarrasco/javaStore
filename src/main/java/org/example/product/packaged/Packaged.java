package org.example.product.packaged;

import org.example.product.Product;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Packaged extends Product {
    private PackageType packageType;
    private boolean imported;

    @Override
    public void setId(String id) {
        Pattern pattern = Pattern.compile("^AB\\d\\d\\d$");
        Matcher matcher = pattern.matcher(id);
        if (matcher.find())
            this.setId(id);
        else
            throw new IllegalArgumentException("Wrong Id format, expected: ABXXX where X = digit");
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
}
