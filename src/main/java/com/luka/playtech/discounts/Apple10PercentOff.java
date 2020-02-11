package com.luka.playtech.discounts;

import com.luka.playtech.Basket;
import com.luka.playtech.Discount;
import com.luka.playtech.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class Apple10PercentOff implements Discount {
    //discount % to be applied to apples
    private static final BigDecimal DISCOUNT_PERCENT = new BigDecimal("10").divide(new BigDecimal("100"));

    //discount name
    private String name;

    public Apple10PercentOff() {

        name = "Apples 10% off:  \t ";
    }

    //maybe it will need changing in the future
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * implementation of {@link Discount#isApplicable isApplicable }
     * @param basket HashMap of products and their counts in the basket
     * @param store products in store
     * @return true if this discount can be applied to the basket
     */
    @Override
    public boolean isApplicable(Map<Product, Integer> basket, Map<String, Product> store) {
        Product apple = store.get("Apple");
        if(basket.containsKey(apple)) {
            return true;
        }
        return false;
    }


    /**
     *
     * Applies the discount by calculating @Link #DISCOUNT_PERCENT DISCOUNT_PERCENT to the price of the apples
     * @param basket HashMap of products and their counts in the basket
     * @param store products in store
     * @return
     */
    @Override
    public BigDecimal applyDiscount(Map<Product,Integer> basket, Map<String, Product> store) {
        Product apple = store.get("Apple");

        if(basket.containsKey(apple)) {
            BigDecimal discount = new BigDecimal(basket.get(apple)).multiply(apple.getPrice()).multiply(DISCOUNT_PERCENT);
            System.out.println(this.name + Discount.parsePriceNumber(discount));
            return discount.setScale(2, RoundingMode.HALF_UP);
        } else {
            return BigDecimal.ZERO;
        }

    }
}
