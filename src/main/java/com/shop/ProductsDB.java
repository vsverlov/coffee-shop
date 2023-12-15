package com.shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.shop.model.Product;
import com.shop.model.ProductType;

/**
 * @author Vitaliy Sverlov
 */
public class ProductsDB {

    private static final Map<String, Product> PRODUCTS = init();

    private static Map<String, Product> init() {
        Product extraMilk = new Product("Extra milk", 0.32, ProductType.Extra);
        Product foamedMilk = new Product("Foamed milk", 0.51, ProductType.Extra);
        Product roastCoffee = new Product("Special roast coffee", 0.95, ProductType.Extra);

        List<Product> coffeeExtras = List.of(extraMilk, foamedMilk, roastCoffee);

        Product coffeeSmall = new Product("Coffee small", 2.55, ProductType.Beverage, coffeeExtras);
        Product coffeeMedium = new Product("Coffee medium", 3.05, ProductType.Beverage, coffeeExtras);
        Product coffeeLarge = new Product("Coffee large", 3.55, ProductType.Beverage, coffeeExtras);
        Product baconRoll = new Product("Bacon Roll", 4.53, ProductType.Snack);
        Product orangeJuice = new Product("Freshly squeezed orange juice (0.25l)", 3.95, ProductType.Beverage);

        Map<String, Product> indexes = new HashMap<>();
        indexes.put("small coffee", coffeeSmall);
        indexes.put("medium coffee", coffeeMedium);
        indexes.put("large coffee", coffeeLarge);
        indexes.put("bacon roll", baconRoll);
        indexes.put("orange juice", orangeJuice);
        indexes.put("extra milk", extraMilk);
        indexes.put("foamed milk", foamedMilk);
        indexes.put("special roast", roastCoffee);

        return indexes;
    }

    public static Product findProduct(String productKey, List<String> extras) {
        Product found = PRODUCTS.get(productKey);
        if (found == null) {
            throw new IllegalArgumentException(String.format("Product '%s' not found", productKey));
        }
        return new Product(found.getName(), found.getPrice(), found.getType(), extras.stream()
                .map(PRODUCTS::get)
                .filter(Objects::nonNull)
                .map(extra -> new Product(extra.getName(), extra.getPrice(), extra.getType()))
                .toList());
    }
}
