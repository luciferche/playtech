package com.luka.playtech.discounts;

import com.luka.playtech.Basket;
import com.luka.playtech.Discount;
import com.luka.playtech.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class HalfBreadForTwoTinSoups implements Discount {

    private static final BigDecimal DISCOUNT_COEFFICIENT = new BigDecimal("0.50");
    private String name;

    public HalfBreadForTwoTinSoups() {
        this.name = "Half bread for 2 tin soups";
    }


    /**
     * implementation of {@link Discount#isApplicable isApplicable }
     * @param basket HashMap of products and their counts in the basket
     * @param store products in store
     * @return true if this discount can be applied to the basket ->
     * if basket contains 2 or more soups
     */
    @Override
    public boolean isApplicable(Map<Product, Integer> basket, Map<String, Product> store) {
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
     *
     * Applies the discount by calculating number of soup tins and if there are 2 or more
     * I apply discount as many times as there are packs of tins
     * if the overall discount for the soup is bigger than the price of the bread in the basket
     * price of bread is returned as maximum discount
     * @param basket HashMap of products and their counts in the basket
     * @return
     */
    @Override
    public BigDecimal applyDiscount(Map<Product, Integer> basket, Map<String, Product> store) {

        Product soup = store.get("Soup");
        Product bread = store.get("Bread");
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
