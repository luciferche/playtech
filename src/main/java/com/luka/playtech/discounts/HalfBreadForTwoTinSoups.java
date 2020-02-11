package com.luka.playtech.discounts;

import com.luka.playtech.Discount;
import com.luka.playtech.Product;

import java.util.HashMap;

public class HalfBreadForTwoTinSoups implements Discount {
    @Override
    public double applyDiscount(HashMap<Product, Integer> basket) {

        return 0d;
    }

    @Override
    public boolean isApplicable(HashMap<Product, Integer> basket) {
        return false;
    }
}
