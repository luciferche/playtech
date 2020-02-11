package com.luka.playtech.discounts;

import com.luka.playtech.Discount;
import com.luka.playtech.Product;

import java.math.BigDecimal;
import java.util.HashMap;

public class HalfBreadForTwoTinSoups implements Discount {
    @Override
    public BigDecimal applyDiscount(HashMap<Product, Integer> basket) {

        return BigDecimal.ZERO;
    }

    @Override
    public boolean isApplicable(HashMap<Product, Integer> basket) {
        return false;
    }
}
