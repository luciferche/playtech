package com.luka.playtech;

import java.util.HashMap;

public interface Discount {


    /**
     * static method implementation now allowed in Java
     * Seemed like most appropriate place to put price formatting as it should to be the same for all of the discounts
     * @param price
     * @return
     */
    static String parsePriceNumber(double price) {
        String doubleAsString = String.valueOf(price);
        if(price > 0) {
            return "£" + doubleAsString;
        } else {
            int indexOfDecimal = doubleAsString.indexOf(".");
            return doubleAsString.substring(indexOfDecimal) + "p";
        }
    }

    /**
     *
     * @param basket HashMap of products and their counts in the basket
     * @return double value of discounts applied
     */
    double applyDiscount(HashMap<Product,Integer> basket);

    /**
     * Checks if the discount can be applied to the basket
     * @param basket HashMap of products and their counts in the basket
     * @return true if this discount can be applied to the basket
     */
    boolean isApplicable(HashMap<Product,Integer> basket);

}
