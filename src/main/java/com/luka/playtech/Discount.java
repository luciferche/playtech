package com.luka.playtech;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public interface Discount {


    /**
     * static method implementation now allowed in Java
     * Seemed like most appropriate place to put price formatting as it should to be the same for all of the discounts
     * @param price
     * @return
     */
    static String parsePriceNumber(BigDecimal price) {
        if(price.compareTo(BigDecimal.ONE) > 0) {
            return "£" + price.setScale(2, RoundingMode.HALF_UP);
        } else {
            return price.remainder( BigDecimal.ONE ).setScale(2, RoundingMode.HALF_UP) + "p";
        }
    }

    /**
     *
     * @param basket HashMap of products and their counts in the basket
     * @return double value of discounts applied
     */
    BigDecimal applyDiscount(HashMap<Product,Integer> basket);

    /**
     * Checks if the discount can be applied to the basket
     * @param basket HashMap of products and their counts in the basket
     * @return true if this discount can be applied to the basket
     */
    boolean isApplicable(HashMap<Product,Integer> basket);

}
