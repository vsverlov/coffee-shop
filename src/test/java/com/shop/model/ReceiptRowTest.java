package com.shop.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReceiptRowTest {

    @Test
    public void shouldPrintTotal() {
        ReceiptRow row = new ReceiptRow("Total", ReceiptRowType.Total, 10.99);
        assertEquals("|                                        Total:    10.99 CHF |", row.print());
    }

    @Test
    public void shouldPrintOffering() {
        ReceiptRow row = new ReceiptRow("Coffee large", ReceiptRowType.Offering, 10.99);
        assertEquals("| Coffee large                                     10.99 CHF |", row.print());
    }

    @Test
    public void shouldPrintExtra() {
        ReceiptRow row = new ReceiptRow("Extra milk", ReceiptRowType.Extra, 10.99);
        assertEquals("|   Extra milk                                     10.99 CHF |", row.print());
    }

    @Test
    public void shouldPrintBonus() {
        ReceiptRow row = new ReceiptRow("Extra's is free (1x)", ReceiptRowType.BonusProgram, 10.99);
        assertEquals("| Extra's is free (1x)                            -10.99 CHF |", row.print());
    }
}
