package com.luka.playtech;


import com.luka.playtech.discounts.Apple10PercentOff;
import com.luka.playtech.discounts.HalfBreadForTwoTinSoups;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        Assert.assertTrue(basket.getSubtotal() == 2.95);
        Assert.assertTrue(basket.getDiscountSum() == 0);
        Assert.assertTrue(basket.getTotal() == 2.85);
    }

    @Test
    public void testAppleDiscounnt() {
        System.out.println("test apple discount");
        basket.addItem("Apple");
        basket.addItem("Soup");
        basket.addItem("Milk");
        basket.calculatePrice();
        Assert.assertTrue(basket.getSubtotal() == 2.95);
        Assert.assertTrue(basket.getDiscountSum() == 0.1);
        Assert.assertTrue(basket.getTotal() == 2.85);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testNonExistingElements() {
        System.out.println("test adding elements");
        basket.addItem("Apples");

        basket.calculatePrice();
    }

}
