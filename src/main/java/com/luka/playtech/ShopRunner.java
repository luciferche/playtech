package com.luka.playtech;

import com.luka.playtech.discounts.Apple10PercentOff;
import com.luka.playtech.discounts.HalfBreadForTwoTinSoups;

import java.util.Scanner;

public class ShopRunner {

    private static final String BASKET_START_DELIMITER = "PriceBasket";
    private static final String EMPTY_STRING = " ";

//    private static final String MESSAGE_END_DELIMITER = "";

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(System.in);

        System.out.println("----------------------------------------------------");
        System.out.println("Enter PriceBasket foolowed by list of items to check against the book");
        System.out.println("----------------------------------------------------");

        //reading of the message
        while(scanner.hasNextLine()) {
            String message = scanner.nextLine();
            if(message.trim().startsWith(BASKET_START_DELIMITER)) {

                String[] items = message.replace(BASKET_START_DELIMITER, "").trim().split(EMPTY_STRING);

                Basket basket = new Basket(Shop.productsInStore);
                basket.addAvailableDiscount(new Apple10PercentOff());
                basket.addAvailableDiscount(new HalfBreadForTwoTinSoups());

                for (int i = 0; i < items.length; i++) {
                    try {
                        basket.addItem(items[i]);
                    } catch(IllegalArgumentException e) {
                        System.out.println("Product not found - " + items[i]);
                    }
                }
                basket.calculatePrice();
            } else {
                break;
            }
        }
    }
}
