package com.shop.service;

import java.util.ArrayList;
import java.util.List;

import com.shop.model.Product;
import com.shop.model.Receipt;
import com.shop.model.ReceiptRow;
import com.shop.model.ReceiptRowType;

/**
 * @author Vitaliy Sverlov
 */
public class ReceiptService {

    public Receipt createReceipt(List<Product> productList, int stampCards) {
        ArrayList<ReceiptRow> rows = new ArrayList<>();
        for (Product product : productList) {
            rows.add(new ReceiptRow(product.getName(), ReceiptRowType.Offering, product.getPrice()));
            rows.addAll(product.getExtras().stream().map(extra -> new ReceiptRow(extra.getName(), ReceiptRowType.Extra, extra.getPrice())).toList());
        }
        rows.addAll(BonusProgram.apply(productList, stampCards));
        return new Receipt(rows);
    }
}
