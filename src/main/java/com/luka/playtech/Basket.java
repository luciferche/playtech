package com.luka.playtech;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {




    private Map<Product, Integer> basket;            //items placed in the basket, key=product value=count
    private List<Discount> discounts;                   //list of discounts available
    private BigDecimal subtotal;                            //subtotal of items before discount
    private BigDecimal discountSum;                         //sum of discounts
    private BigDecimal total;                               // total price for the basket after discounts

    private Map<String, Product> store;

    public Basket(Map<String, Product> store) {
        this.basket = new HashMap<>();
        this.discounts = new ArrayList<>();
        this.subtotal = new BigDecimal("0");
        this.total = new BigDecimal("0");
        this.discountSum = new BigDecimal("0");

        this.store = store;
    }

    /**
     * Adding item by name to the basket
     * @param productName
     * @throws IllegalArgumentException when product with that name hasn't been found in the shop
     */
    public void addItem(String productName) throws IllegalArgumentException{
        Product product = store.get(productName);
        if(product == null) {
            throw new IllegalArgumentException("Cannot find product specified");
        }
        this.addItem(product);
    }

    /**
     * Helper method to increment count of a product in the basket
     * @param product
     */
    private void addItem(Product product) {

//        System.out.println("add  product " + product.getName());
        int count = basket.getOrDefault(product, 0);
        count++;
        basket.put(product,count);
    }

    /**
     * Main method to calculate final price and process discounts
     */
    public void calculatePrice() {
        this.subtotal = basket.keySet()
                .stream()
                .map(product -> product.getPrice().multiply(new BigDecimal(basket.get(product))))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.total = this.subtotal;
        System.out.println("Subtotal: \t\t " + this.subtotal);

        processDiscounts();

        //subtracting the discount from the total if it is bigger than 0
        if(this.discountSum.compareTo(BigDecimal.ZERO) > 0) {
            this.total = this.total.subtract(this.discountSum);
        }

        System.out.println("---------------------------------------------");
        System.out.println("Total: \t\t " + this.total);

    }

    /**
     * Applies all the discounts in the list and updates discounts sum for the basket
     */
    private void processDiscounts() {
        this.discountSum = discounts
                .stream()
                .filter(discount -> discount.isApplicable(basket, store))
                .map(discount -> discount.applyDiscount(basket, store))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public BigDecimal getDiscountSum() {
        return discountSum;
    }

    public BigDecimal getTotal() {
        return total;
    }

    /**
     * Adds discount implementation to the list of discounts available for the basket
     * @param discount Implementation of Discount interface e.g. Apple10PercentOff
     */
    public void addAvailableDiscount(Discount discount) {
        this.discounts.add(discount);
    }
}
