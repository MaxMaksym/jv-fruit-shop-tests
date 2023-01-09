package core.basesyntax.service.impl;

import static org.junit.Assert.assertTrue;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.FruitTransactionService;
import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Test;

public class FruitTransactionServiceImplTest {
    private static final FruitTransactionService fruitTransactionService =
            new FruitTransactionServiceImpl();
    private static final String BANANA = "banana";
    private static final String APPLE = "apple";
    private static final String GRAPE = "grape";

    @AfterClass
    public static void cleanStorage() {
        Storage.fruits.clear();
    }

    @Test
    public void update_validInput_ok() {
        List<FruitTransaction> input = List.of(
                new FruitTransaction(FruitTransaction.Operation.BALANCE, BANANA, 1),
                new FruitTransaction(FruitTransaction.Operation.BALANCE, APPLE, 99),
                new FruitTransaction(FruitTransaction.Operation.BALANCE, GRAPE, 400),
                new FruitTransaction(FruitTransaction.Operation.PURCHASE, BANANA, 1),
                new FruitTransaction(FruitTransaction.Operation.PURCHASE, APPLE, 99),
                new FruitTransaction(FruitTransaction.Operation.PURCHASE, GRAPE, 400),
                new FruitTransaction(FruitTransaction.Operation.RETURN, BANANA, 1),
                new FruitTransaction(FruitTransaction.Operation.RETURN, APPLE, 99),
                new FruitTransaction(FruitTransaction.Operation.RETURN, GRAPE, 400),
                new FruitTransaction(FruitTransaction.Operation.SUPPLY, BANANA, 1),
                new FruitTransaction(FruitTransaction.Operation.SUPPLY, APPLE, 99),
                new FruitTransaction(FruitTransaction.Operation.SUPPLY, GRAPE, 400)
        );
        fruitTransactionService.update(input);
        assertTrue("invalid result of method",
                Storage.fruits.containsKey(BANANA) && Storage.fruits.get(BANANA) == 2);
        assertTrue("invalid result of method",
                Storage.fruits.containsKey(APPLE) && Storage.fruits.get(APPLE) == 198);
        assertTrue("invalid result of method",
                Storage.fruits.containsKey(GRAPE) && Storage.fruits.get(GRAPE) == 800);
    }

    @Test(expected = RuntimeException.class)
    public void update_nullInput_ok() {
        fruitTransactionService.update(null);
    }

    @Test(expected = RuntimeException.class)
    public void update_emptyInput_ok() {
        fruitTransactionService.update(new ArrayList<>());
    }
}