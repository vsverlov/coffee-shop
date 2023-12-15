package com.shop.model;

/**
 * @author Vitaliy Sverlov
 */
public enum ReceiptRowType {
    Offering,
    Extra,
    BonusProgram,
    Total;

    public boolean isOrderItem() {
        return this == Offering || this == Extra;
    }
}
