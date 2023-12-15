package com.shop.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.shop.model.Product;
import com.shop.model.ProductType;
import com.shop.model.ReceiptRow;
import com.shop.model.ReceiptRowType;

/**
 * @author Vitaliy Sverlov
 */
public class BonusProgram {

    public static List<ReceiptRow> apply(List<Product> productList, int stampCards) {
        List<ReceiptRow> result = new ArrayList<>();
        applyFreeDrinks(productList, stampCards).ifPresent(result::add);
        applyFreeExtra(productList).ifPresent(result::add);
        return result;
    }

    private static Optional<ReceiptRow> applyFreeDrinks(List<Product> productList, int stampCards) {
        List<Product> beverages = productList.stream()
                .filter(product -> product.getType() == ProductType.Beverage)
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .toList();
        int freeDrinks = Math.min(stampCards, beverages.size());
        if (freeDrinks > 0) {
            double discount = beverages.subList(0, freeDrinks).stream().mapToDouble(product -> product.getPrice()).sum();
            ReceiptRow freeDrinkdRow = new ReceiptRow(String.format("5th beverage is for free (%sx)", freeDrinks), ReceiptRowType.BonusProgram,
                    discount);
            return Optional.of(freeDrinkdRow);
        }
        return Optional.empty();
    }

    private static Optional<ReceiptRow> applyFreeExtra(List<Product> productList) {
        Map<ProductType, List<Product>> productsByType = productList.stream()
                .collect(Collectors.groupingBy(Product::getType));
        if (productsByType.containsKey(ProductType.Snack) && productsByType.containsKey(ProductType.Beverage)) {
            List<Product> beverages = productsByType.get(ProductType.Beverage)
                    .stream()
                    .filter(product -> !product.getExtras().isEmpty())
                    .collect(Collectors.toList());
            if (beverages.isEmpty()) {
                return Optional.empty();
            }
            double discount = 0.0;
            int freeExtra = 0;
            for (; !beverages.isEmpty() && freeExtra < productsByType.get(ProductType.Snack).size(); freeExtra++) {
                Product extra = beverages.get(0).getExtras().stream().min(Comparator.comparingDouble(Product::getPrice)).orElseThrow();
                discount += extra.getPrice();
                beverages.remove(0);
            }
            return Optional.of(new ReceiptRow(String.format("Extra's is free (%sx)", freeExtra), ReceiptRowType.BonusProgram, discount));
        }
        return Optional.empty();
    }
}
