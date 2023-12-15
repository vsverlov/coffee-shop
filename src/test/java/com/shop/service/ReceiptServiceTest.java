package com.shop.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.shop.model.Product;
import com.shop.model.ProductType;
import com.shop.model.Receipt;
import org.junit.Test;

public class ReceiptServiceTest {

    ReceiptService victim = new ReceiptService();

    @Test
    public void shouldCreateReceipt() {

        List<Product> products = List.of(new Product("Test", 1.99, ProductType.Beverage, List.of(
                        new Product("extra 1", 0.59, ProductType.Extra))),
                new Product("Burger", 1.99, ProductType.Beverage));

        Receipt result = victim.createReceipt(products, 0);

        assertEquals(result.getRows().get(0).getName(), "Test");
        assertEquals(result.getRows().get(1).getName(), "extra 1");
        assertEquals(result.getRows().get(2).getName(), "Burger");

    }
}
