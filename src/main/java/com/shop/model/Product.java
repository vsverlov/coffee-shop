package com.shop.model;

import java.util.List;
import java.util.Objects;

/**
 * @author Vitaliy Sverlov
 */
public class Product {

    private final String name;

    private final double price;

    private final ProductType type;

    private final List<Product> extras;

    public Product(final String name, final double price, final ProductType type) {
        this.name = name;
        this.price = price;
        this.type = type;
        extras = List.of();
    }

    public Product(final String name, final double price, final ProductType type, final List<Product> extras) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.extras = extras;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public ProductType getType() {
        return type;
    }

    public List<Product> getExtras() {
        return extras;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product product)) {
            return false;
        }
        return Double.compare(price, product.price) == 0 && Objects.equals(name, product.name) && type == product.type && Objects.equals(extras,
                product.extras);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, type, extras);
    }
}
