package com.luka.playtech;

import java.util.HashMap;

public interface Discount {
    double applyDiscount(HashMap<Product,Integer> basket);
    boolean isApplicable(HashMap<Product,Integer> basket);

}
