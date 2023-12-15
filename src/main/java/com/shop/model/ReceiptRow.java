package com.shop.model;

import static com.shop.model.ReceiptRowFormatter.format;
import static com.shop.model.ReceiptRowFormatter.leftPadding;
import static com.shop.model.ReceiptRowFormatter.rightPadding;

import java.util.Optional;

/**
 * @author Vitaliy Sverlov
 */
public class ReceiptRow {

    private final String name;

    private final ReceiptRowType type;

    private final Optional<Double> price;

    public ReceiptRow(final String name, final ReceiptRowType type, final double price) {
        this.name = name;
        this.type = type;
        this.price = Optional.of(price);
    }

    public String getName() {
        return name;
    }

    public ReceiptRowType getType() {
        return type;
    }

    public Optional<Double> getPrice() {
        return price;
    }

    public String print() {
        double price = this.getPrice().orElse(0.0);
        return switch (type) {
            case Offering -> "| " + rightPadding(name, 46) + leftPadding(format(price), 12) + " |";
            case Extra -> "| " + rightPadding("  " + name, 46) + leftPadding(format(price), 12) + " |";
            case BonusProgram -> "| " + rightPadding(name, 46) + leftPadding(format(-price), 12) + " |";
            case Total -> "| " + leftPadding("Total: ", 46) + leftPadding(format(price), 12) + " |";
        };
    }

}
