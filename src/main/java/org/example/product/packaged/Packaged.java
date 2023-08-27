package org.example.product.packaged;

import org.example.product.Product;

public class Packaged extends Product {
    private PackageType packageType;
    private boolean imported;

    @Override
    public void setId(String id) {
        if(id.charAt(1) != 'B') {
            throw new IllegalArgumentException("Wrong Id format.");
        } else {
            super.setId(id);
        }    }

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
