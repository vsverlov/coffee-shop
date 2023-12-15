package com.shop.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReceiptRowFormatterTest {

    @Test
    public void shouldFormat() {
        assertEquals("99.99 CHF", ReceiptRowFormatter.format(99.99));
        assertEquals("99.00 CHF", ReceiptRowFormatter.format(99));
        assertEquals("199.01 CHF", ReceiptRowFormatter.format(199.01));
    }

    @Test
    public void shouldRightPadding() {
        assertEquals("abc  ", ReceiptRowFormatter.rightPadding("abc", 5));
    }

    @Test
    public void shouldLeftPadding() {
        assertEquals("  abc", ReceiptRowFormatter.leftPadding("abc", 5));
    }

    @Test
    public void shouldCopy() {
        assertEquals("aaaaa", ReceiptRowFormatter.copy('a', 5));
    }
}
