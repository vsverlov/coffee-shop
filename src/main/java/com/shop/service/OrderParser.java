package com.shop.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.shop.ProductsDB;
import com.shop.model.Product;

/**
 * @author Vitaliy Sverlov
 */
public class OrderParser {

    public List<Product> parse(String orderLine) {
        ArrayList<Product> products = new ArrayList<>();
        try (Scanner scanner = new Scanner(orderLine).useDelimiter(",")) {
            while (scanner.hasNext()) {
                String item = scanner.next();
                products.add(parseProduct(item));
            }
        }
        return products;
    }

    private Product parseProduct(String productItem) {
        String[] items = trim(productItem).split(" with ");
        String productKey = items[0];
        String extras = items.length > 1 ? items[1] : null;
        Product product = ProductsDB.findProduct(productKey, getExtras(extras));
        return product;
    }

    private List<String> getExtras(String extras) {
        if (extras == null) {
            return List.of();
        }
        return Arrays.asList(extras.split(" and "));
    }

    private String trim(String str) {
        return str != null ? str.trim() : str;
    }
}
