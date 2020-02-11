package com.luka.playtech.discounts;

import com.luka.playtech.Basket;
import com.luka.playtech.Discount;
import com.luka.playtech.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

public class HalfBreadForTwoTinSoups implements Discount {

    private static final BigDecimal DISCOUNT_COEFFICIENT = new BigDecimal("0.50");
    private String name;

    public HalfBreadForTwoTinSoups() {
        this.name = "Half bread for 2 tin soups";
    }

    @Override
    public boolean isApplicable(HashMap<Product, Integer> basket) {
        Product soup = Basket.productsInStore.get("Soup");
        Product bread = Basket.productsInStore.get("Bread");
        if(basket.getOrDefault(soup,0) >= 2) {
            if(basket.get(bread) != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * 10 s - 5 b
     * @param basket
     * @return
     */

    @Override
    public BigDecimal applyDiscount(HashMap<Product, Integer> basket) {

        Product soup = Basket.productsInStore.get("Soup");
        Product bread = Basket.productsInStore.get("Bread");
        int countSoup = basket.get(soup);
        int countBread = basket.get(bread);
        int timesDiscount = countSoup / 2;
        BigDecimal shouldDiscount = new BigDecimal(timesDiscount)
                .multiply(bread.getPrice())
                .multiply(DISCOUNT_COEFFICIENT);

        BigDecimal breadPrice = new BigDecimal(countBread).multiply(bread.getPrice());
        if(shouldDiscount.compareTo(breadPrice) < 0) {
            System.out.println(this.name + " " + Discount.parsePriceNumber(shouldDiscount) + "");
            return shouldDiscount.setScale(2, RoundingMode.HALF_UP);
        } else {
            System.out.println(this.name + " " + Discount.parsePriceNumber(breadPrice));
            return breadPrice; // discount bigger than price of bread so just return price of all bread
        }

    }

}
