package com.luka.playtech.discounts;

import com.luka.playtech.Basket;
import com.luka.playtech.Discount;
import com.luka.playtech.Product;

import java.util.HashMap;

public class Apple10PercentOff implements Discount {
    //discount % to be applied to apples
    private static final double DISCOUNT = 10;

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
    public double applyDiscount(HashMap<Product,Integer> basket) {
        Product apple = Basket.productsInStore.get("Apple");

        if(basket.containsKey(apple)) {
            double discount = basket.get(apple) * apple.getPrice() * DISCOUNT / 100;
            System.out.println(this.name + Discount.parsePriceNumber(discount));
            return discount;
        } else {
            return 0;
        }

    }
}
