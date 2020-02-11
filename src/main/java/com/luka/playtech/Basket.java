package com.luka.playtech;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Basket {
    public static HashMap<String,Product> productsInStore = new HashMap<>();

    static {
        productsInStore.put("Apple", new Product("Apples", 1.00));
        productsInStore.put("Soup", new Product("Soup", 0.65));
        productsInStore.put("Milk", new Product("Milk", 1.3));
        productsInStore.put("Bread", new Product("Bread",  0.8));

    }
    public HashMap<Product, Integer> basket;
    private List<Discount> discounts;
    private double subtotal;


    public Basket() {
        this.basket = new HashMap<>();
        this.discounts = new ArrayList<>();
        this.subtotal = 0;
    }

    public void addItem(Product product) {
        System.out.println("add  product " + product.getName());
        int count = basket.getOrDefault(product,0);
        count++;
        basket.put(product,count);
    }

    public void getTotal() {

        this.subtotal = basket.keySet()
                .stream()
                .mapToDouble(product -> product.getPrice() * basket.get(product))
                .sum();

        System.out.println("Subtotal: \t\t " + this.subtotal);


    }
}
