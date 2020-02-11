package com.luka.playtech;

import java.util.Objects;

public class Product {

    private double price;
    private String name;

    public Product(String name) {
        this.name = name;
        this.price = 0;
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return this.name;
    }

    /*
        I'm using the object as a key in a hashmap so I need to override hashCode and equals method
        Using string property name for equality
    */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
