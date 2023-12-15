/*
 * @(#)Logger.java
 *
 * Copyright Swiss Reinsurance Company, Mythenquai 50/60, CH 8022 Zurich. All rights reserved.
 */

package com.shop;

/**
 * @author Vitaliy Sverlov
 */
public final class Logger {

    public static void info(Object value) {
        System.out.println(value);
    }

    public static void error(Object value) {
        System.err.println(value);
    }
}
