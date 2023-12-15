package com.shop.model;

import static com.shop.model.ReceiptRowFormatter.copy;

import java.util.List;
import java.util.Optional;

/**
 * @author Vitaliy Sverlov
 */
public class Receipt {

    private List<ReceiptRow> rows;

    private ReceiptRow total;

    public Receipt(final List<ReceiptRow> rows) {
        this.rows = rows;
    }

    public List<ReceiptRow> getRows() {
        return rows;
    }

    public ReceiptRow getTotal() {
        if (this.total == null) {
            double itemsPrice = rows.stream().filter(row -> row.getType().isOrderItem())
                    .map(ReceiptRow::getPrice)
                    .filter(Optional::isPresent)
                    .mapToDouble(Optional::get)
                    .sum();
            double bonus = rows.stream().filter(row -> row.getType() == ReceiptRowType.BonusProgram)
                    .map(ReceiptRow::getPrice)
                    .filter(Optional::isPresent)
                    .mapToDouble(Optional::get)
                    .sum();
            this.total = new ReceiptRow("Total", ReceiptRowType.Total, itemsPrice - bonus);
        }
        return this.total;
    }

    public String print() {
        final StringBuilder builder = new StringBuilder();
        builder.append(copy('_', 62)).append('\n');
        builder.append('|').append(copy(' ', 60)).append('|').append('\n');
        rows.forEach(row -> builder.append(row.print()).append('\n'));
        builder.append('|').append(copy(' ', 60)).append('|').append('\n');
        builder.append(getTotal().print()).append('\n');
        builder.append('|').append(copy('_', 60)).append('|').append('\n');
        return builder.toString();
    }
}
