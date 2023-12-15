package com.shop.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.shop.model.Product;
import com.shop.model.ProductType;
import com.shop.model.ReceiptRow;
import com.shop.model.ReceiptRowType;
import org.junit.Test;

public class BonusProgramTest {

    @Test
    public void shouldApplyFreeDrink() {
        List<Product> products = List.of(new Product("Large", 1.99, ProductType.Beverage),
                new Product("Small", 0.99, ProductType.Beverage),
                new Product("Snack", 0.50, ProductType.Snack));
        List<ReceiptRow> result = BonusProgram.apply(products, 1);
        assertEquals(1, result.size());
        ReceiptRow row = result.get(0);
        assertSame(row.getType(), ReceiptRowType.BonusProgram);
        assertEquals(0.99, row.getPrice().orElseThrow(), 0.0);
        assertEquals("5th beverage is for free (1x)", row.getName());

        result = BonusProgram.apply(products, 2);
        assertEquals(1, result.size());
        row = result.get(0);
        assertSame(row.getType(), ReceiptRowType.BonusProgram);
        assertEquals(0.99 + 1.99, row.getPrice().orElseThrow(), 0.0);
        assertEquals("5th beverage is for free (2x)", row.getName());
    }

    @Test
    public void shouldApplyFreeExtraEmpty() {
        List<Product> products = List.of(new Product("Test", 1.99, ProductType.Beverage),
                new Product("Snack", 0.50, ProductType.Snack));
        List<ReceiptRow> result = BonusProgram.apply(products, 0);
        assertTrue(result.isEmpty());
    }

    @Test
    public void shouldApplyFreeExtra() {
        List<Product> products = List.of(new Product("Drink 1", 1.99, ProductType.Beverage),
                new Product("Drink 2", 2.99, ProductType.Beverage,
                        List.of(new Product("Extra 2", 0.99, ProductType.Extra),
                                new Product("Extra 1", 0.39, ProductType.Extra))),
                new Product("Snack 1", 0.50, ProductType.Snack),
                new Product("Snack 2", 0.80, ProductType.Snack),
                new Product("Snack 3", 0.80, ProductType.Snack));
        List<ReceiptRow> result = BonusProgram.apply(products, 0);
        assertEquals(1, result.size());
        ReceiptRow row = result.get(0);
        assertSame(row.getType(), ReceiptRowType.BonusProgram);
        assertEquals(0.39, row.getPrice().orElseThrow(), 0.0);
        assertEquals("Extra's is free (1x)", row.getName());
    }

    @Test
    public void shouldApplyFreeExtraMoreThanOne() {
        List<Product> products = List.of(new Product("Drink 1", 1.99, ProductType.Beverage,
                        List.of(new Product("Extra 2", 0.79, ProductType.Extra),
                                new Product("Extra 1", 0.59, ProductType.Extra))),
                new Product("Drink 2", 2.99, ProductType.Beverage,
                        List.of(new Product("Extra 2", 0.99, ProductType.Extra),
                                new Product("Extra 1", 0.39, ProductType.Extra))),
                new Product("Snack 1", 0.50, ProductType.Snack),
                new Product("Snack 2", 0.80, ProductType.Snack),
                new Product("Snack 3", 0.80, ProductType.Snack));
        List<ReceiptRow> result = BonusProgram.apply(products, 0);
        assertEquals(1, result.size());
        ReceiptRow row = result.get(0);
        assertSame(row.getType(), ReceiptRowType.BonusProgram);
        assertEquals(0.59 + 0.39, row.getPrice().orElseThrow(), 0.0);
        assertEquals("Extra's is free (2x)", row.getName());
    }

    @Test
    public void shouldApplyFreeExtraOnce() {
        List<Product> products = List.of(new Product("Drink 1", 1.99, ProductType.Beverage,
                        List.of(new Product("Extra 2", 0.79, ProductType.Extra),
                                new Product("Extra 1", 0.59, ProductType.Extra))),
                new Product("Drink 2", 2.99, ProductType.Beverage,
                        List.of(new Product("Extra 2", 0.99, ProductType.Extra),
                                new Product("Extra 1", 0.39, ProductType.Extra))),
                new Product("Snack 1", 0.50, ProductType.Snack));
        List<ReceiptRow> result = BonusProgram.apply(products, 0);
        assertEquals(1, result.size());
        ReceiptRow row = result.get(0);
        assertSame(row.getType(), ReceiptRowType.BonusProgram);
        assertEquals(0.59, row.getPrice().orElseThrow(), 0.0);
        assertEquals("Extra's is free (1x)", row.getName());
    }
}
