package com.shop;

import java.io.Console;
import java.util.List;

import com.shop.model.Product;
import com.shop.model.Receipt;
import com.shop.service.OrderParser;
import com.shop.service.ReceiptService;

public class Application {

    private final OrderParser parser = new OrderParser();

    private final ReceiptService receiptService = new ReceiptService();

    public Receipt executeOrder(String request, int stampCards) {
        List<Product> products = parser.parse(request);
        Receipt receipt = receiptService.createReceipt(products, stampCards);
        return receipt;
    }

    public static void main(String[] args) {
        Console console = System.console();
        String orderLine = console.readLine("Enter your order: ");
        int stampCards = 0;
        try {
            stampCards = Integer.parseInt(console.readLine("Stamp card(s): "));
        } catch (NumberFormatException e) {
            Logger.error("Unable to parse Stamp cards quantity. Will proceed with none");

        }
        Receipt receipt = new Application().executeOrder(orderLine, stampCards);
        Logger.info(receipt.print());
    }
}
