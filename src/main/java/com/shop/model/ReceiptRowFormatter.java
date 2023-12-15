/*
 * @(#)ReceiptRowFormatter.java
 *
 * Copyright Swiss Reinsurance Company, Mythenquai 50/60, CH 8022 Zurich. All rights reserved.
 */

package com.shop.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * @author Vitaliy Sverlov
 */
public final class ReceiptRowFormatter {

    private final static DecimalFormat MONEY_FORMAT = init();

    private static DecimalFormat init() {
        DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();
        unusualSymbols.setDecimalSeparator('.');
        unusualSymbols.setGroupingSeparator(',');

        DecimalFormat moneyFormat = new DecimalFormat("######0.00 CHF");
        moneyFormat.setDecimalFormatSymbols(unusualSymbols);
        return moneyFormat;
    }

    public static String format(double value) {
        return MONEY_FORMAT.format(value);
    }

    public static String rightPadding(String value, int size) {
        int missing = size - value.length();
        return value + copy(' ', missing);
    }

    public static String leftPadding(String value, int size) {
        int missing = size - value.length();
        return copy(' ', missing) + value;
    }

    public static String copy(char ch, int times) {
        return String.valueOf(ch).repeat(Math.max(0, times));
    }
}
