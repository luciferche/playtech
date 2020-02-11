package com.luka.playtech.discounts;

import com.luka.playtech.Basket;
import com.luka.playtech.Discount;
import com.luka.playtech.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

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
     * @return true if this discount can be applied to the basket
     */
    @Override
    public boolean isApplicable(HashMap<Product, Integer> basket) {
        Product apple = Basket.productsInStore.get("Apple");
        if(basket.containsKey(apple)) {
            return true;
        }
        return false;
    }

    @Override
    public BigDecimal applyDiscount(HashMap<Product,Integer> basket) {
        Product apple = Basket.productsInStore.get("Apple");

        if(basket.containsKey(apple)) {
            BigDecimal discount = new BigDecimal(basket.get(apple)).multiply(apple.getPrice()).multiply(DISCOUNT_PERCENT);
            System.out.println(this.name + Discount.parsePriceNumber(discount));
            return discount.setScale(2, RoundingMode.HALF_UP);
        } else {
            return BigDecimal.ZERO;
        }

    }
}
