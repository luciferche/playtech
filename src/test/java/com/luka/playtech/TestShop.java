package com.luka.playtech;


import com.luka.playtech.discounts.Apple10PercentOff;
import com.luka.playtech.discounts.HalfBreadForTwoTinSoups;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class TestShop {

    private Basket basket;

    @Before
    public void setup() {
        basket = new Basket();
        basket.addAvailableDiscount(new Apple10PercentOff());
        basket.addAvailableDiscount(new HalfBreadForTwoTinSoups());
    }

    @Test
    public void testAddingToBasket() {
        System.out.println("test adding elements");
        basket.addItem("Soup");
        basket.addItem("Milk");
        basket.calculatePrice();
        Assert.assertTrue(basket.getSubtotal().equals(new BigDecimal("1.95")));
        Assert.assertTrue(basket.getDiscountSum().equals(BigDecimal.ZERO));
        Assert.assertTrue(basket.getTotal().equals(new BigDecimal("1.95")));
    }

    @Test
    public void testAppleDiscount() {
        System.out.println("test apple discount");
        basket.addItem("Apple");
        basket.addItem("Soup");
        basket.addItem("Milk");
        basket.calculatePrice();

        Assert.assertTrue(basket.getSubtotal().equals(new BigDecimal("2.95")));
        Assert.assertTrue(basket.getDiscountSum().equals(new BigDecimal("0.10")));
        Assert.assertTrue(basket.getTotal().equals(new BigDecimal("2.85")));
    }

    @Test
    public void testHalfBreadForTwoSoupsDiscount() {
        System.out.println("test 1/2 bread for 2 soups discount");
        basket.addItem("Milk");
        basket.addItem("Soup");
        basket.addItem("Soup");
        basket.addItem("Bread");
        basket.calculatePrice();

        Assert.assertTrue(basket.getSubtotal().equals(new BigDecimal("3.40")));
        Assert.assertTrue(basket.getDiscountSum().equals(new BigDecimal("0.40")));
        Assert.assertTrue(basket.getTotal().equals(new BigDecimal("3.00")));
    }

    @Test
    public void testHalfBreadForTwoSoupsDiscount_withoutBread() {
        System.out.println("test 1/2 bread for 2 soups discount");
        basket.addItem("Milk");
        basket.addItem("Soup");
        basket.addItem("Soup");
        basket.calculatePrice();

        Assert.assertTrue(basket.getSubtotal().equals(new BigDecimal("2.60")));
        Assert.assertTrue(basket.getDiscountSum().equals(BigDecimal.ZERO));
        Assert.assertTrue(basket.getTotal().equals(new BigDecimal("2.60")));
    }

    @Test
    public void testBothDiscounts() {
        System.out.println("test both discounts");
        basket.addItem("Apple");
        basket.addItem("Soup");
        basket.addItem("Soup");
        basket.addItem("Bread");
        basket.calculatePrice();

        Assert.assertTrue(basket.getSubtotal().equals(new BigDecimal("3.10")));
        Assert.assertTrue(basket.getDiscountSum().equals(new BigDecimal("0.50")));
        Assert.assertTrue(basket.getTotal().equals(new BigDecimal("2.60")));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testNonExistingElements() {
        System.out.println("test adding elements");
        basket.addItem("Apples");

        basket.calculatePrice();
    }

}
