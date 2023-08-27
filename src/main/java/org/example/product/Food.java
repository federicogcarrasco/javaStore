package org.example.product;

import java.util.Date;

public interface Food {
    Date getExpirationDate();
    void setExpirationDate(Date date);
    short getCalories();
    void setCalories(short calories);
}
