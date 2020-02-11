package com.luka.playtech;

import java.util.HashMap;

public class Shop {
    /*
       static map used to store products available in the store
       This shouldn't stay here as it is not related to the basket but the shop
    */
    public static HashMap<String,Product> productsInStore = new HashMap<>();

    //filling in initial products with prices in the store
    static {
        productsInStore.put("Apple", new Product("Apple", "1.00"));
        productsInStore.put("Soup", new Product("Soup", "0.65"));
        productsInStore.put("Milk", new Product("Milk", "1.30"));
        productsInStore.put("Bread", new Product("Bread",  "0.80"));

    }
    private Shop() {

    }


    /**
     * static method for updating or adding product to the product list
     * @param productName
     * @param price
     */
    public static void updateProductPrice(String productName, String price) {
        productsInStore.put(productName, new Product(productName, price));

    }


}
