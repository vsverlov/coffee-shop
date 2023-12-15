package com.shop.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.shop.model.Product;
import org.junit.Test;

public class OrderParserTest {

    private OrderParser victim = new OrderParser();

    @Test
    public void shoudParseSimple() {

        String orderLine = "large coffee, bacon roll";

        List<Product> result = victim.parse(orderLine);

        assertEquals(2, result.size());
        assertEquals("Coffee large", result.get(0).getName());
        assertEquals("Bacon Roll", result.get(1).getName());
    }

    @Test
    public void shoudParse() {

        String orderLine = "large coffee with extra milk, small coffee with special roast and foamed milk, bacon roll, orange juice";

        List<Product> result = victim.parse(orderLine);

        assertEquals(4, result.size());
        Product coffeeLarge = result.get(0);
        assertEquals("Coffee large", coffeeLarge.getName());
        assertEquals(1, coffeeLarge.getExtras().size());

        Product coffeeSmall = result.get(1);
        assertEquals("Coffee small", coffeeSmall.getName());
        assertEquals(2, coffeeSmall.getExtras().size());

        assertEquals("Bacon Roll", result.get(2).getName());
        assertEquals("Freshly squeezed orange juice (0.25l)", result.get(3).getName());
    }
}
