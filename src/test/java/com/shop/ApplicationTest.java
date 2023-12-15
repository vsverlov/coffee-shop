package com.shop;

import static org.junit.Assert.assertEquals;

import com.shop.model.Receipt;
import com.shop.model.ReceiptRowType;
import org.junit.Test;

public class ApplicationTest {

    private Application victim = new Application();

    @Test
    public void shouldExecuteOrder() {
        Receipt result = victim.executeOrder("large coffee with extra milk, small coffee with special roast, bacon roll, orange juice", 1);
        Logger.info(result.print());

        assertEquals(8, result.getRows().size());

        long offering = result.getRows().stream().filter(row -> row.getType() == ReceiptRowType.Offering).count();
        assertEquals(4, offering);

        long extras = result.getRows().stream().filter(row -> row.getType() == ReceiptRowType.Extra).count();
        assertEquals(2, extras);

        long bonus = result.getRows().stream().filter(row -> row.getType() == ReceiptRowType.BonusProgram).count();
        assertEquals(2, bonus);

        assertEquals(12.98, result.getTotal().getPrice().orElseThrow(), 0.0);

    }

    @Test
    public void shouldExecuteOrder2Cards() {
        Receipt result = victim.executeOrder("large coffee with extra milk, small coffee with special roast, bacon roll, orange juice", 2);
        Logger.info(result.print());

        assertEquals(8, result.getRows().size());

        long offering = result.getRows().stream().filter(row -> row.getType() == ReceiptRowType.Offering).count();
        assertEquals(4, offering);

        long extras = result.getRows().stream().filter(row -> row.getType() == ReceiptRowType.Extra).count();
        assertEquals(2, extras);

        long bonus = result.getRows().stream().filter(row -> row.getType() == ReceiptRowType.BonusProgram).count();
        assertEquals(2, bonus);

        assertEquals(9.43, result.getTotal().getPrice().orElseThrow(), 0.0);

    }
}
