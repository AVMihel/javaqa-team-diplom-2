package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class SavingAccountTest {

    @ParameterizedTest  // Позитивные  тесты на создание счета
    @CsvSource({        // initialBalance, minBalance, maxBalance, rate
            "2000, 1000, 10000, 5",
            "1000, 1000, 10000, 5",
            "10000, 1000, 10000, 5",
            "2000, 1000, 10000, 0"
    })
    public void shouldCreateSavingAccountPositiveTests(int initialBalance, int minBalance, int maxBalance, int rate) {
        SavingAccount account = new SavingAccount(
                initialBalance,
                minBalance,
                maxBalance,
                rate
        );

        Assertions.assertAll(
                () -> assertEquals(initialBalance, account.getBalance()),
                () -> assertEquals(minBalance, account.getMinBalance()),
                () -> assertEquals(maxBalance, account.getMaxBalance()),
                () -> assertEquals(rate, account.getRate())
        );

    }

    @ParameterizedTest  // Негативные тесты на создание счета
    @CsvSource({        // initialBalance, minBalance, maxBalance, rate
            "2000, 20000, 10000, 5",
            "500, 1000, 10000, 5",
            "12000, 1000, 10000, 5",
            "2000, 1000, 10000, -5"
    })
    public void shouldNotCreateSavingAccountNegativeTests(int initialBalance, int minBalance, int maxBalance, int rate) {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    initialBalance,
                    minBalance,
                    maxBalance,
                    rate
            );
        });
    }

    @ParameterizedTest  // Позитивные тесты на оплату покупки
    @CsvSource({        // initialBalance, minBalance, maxBalance, rate, amount
            "5000, 1000, 10000, 5, 2000",
            "5000, 1000, 10000, 5, 4000",
            "5000, 0, 10000, 5, 5000"
    })
    public void shouldPayPositiveTests(int initialBalance, int minBalance, int maxBalance, int rate, int amount) {
        SavingAccount account = new SavingAccount(
                initialBalance,
                minBalance,
                maxBalance,
                rate
        );

        boolean result = account.pay(amount);

        Assertions.assertAll(
                () -> assertEquals(initialBalance - amount, account.getBalance()),
                () -> assertTrue(result)
        );

    }

    @ParameterizedTest  // Негативные тесты на оплату покупки
    @CsvSource({        // initialBalance, minBalance, maxBalance, rate, amount
            "5000, 1000, 10000, 5, 0",
            "5000, 1000, 10000, 5, -1000",
            "5000, 0, 10000, 5, 6000",
            "5000, 1000, 10000, 5, 4500"
    })
    public void shouldNotPayNegativeTests(int initialBalance, int minBalance, int maxBalance, int rate, int amount) {
        SavingAccount account = new SavingAccount(
                initialBalance,
                minBalance,
                maxBalance,
                rate
        );

        boolean result = account.pay(amount);

        Assertions.assertAll(
                () -> assertEquals(initialBalance, account.getBalance()),
                () -> assertFalse(result)
        );
    }

    @ParameterizedTest  // Позитивные тесты на поплнение баланса
    @CsvSource({        // initialBalance, minBalance, maxBalance, rate, amount
            "2000, 1000, 10000, 5, 2000",
            "2000, 1000, 10000, 5, 8000"
    })
    public void shouldAddPositiveTests(int initialBalance, int minBalance, int maxBalance, int rate, int amount) {
        SavingAccount account = new SavingAccount(
                initialBalance,
                minBalance,
                maxBalance,
                rate
        );

        boolean result = account.add(amount);

        Assertions.
                assertAll(
                        () -> assertEquals(initialBalance + amount, account.getBalance()),
                        () -> assertTrue(result)
                );
    }

    @ParameterizedTest  // Негативные тесты на поплнение баланса
    @CsvSource({        // initialBalance, minBalance, maxBalance, rate, amount
            "2000, 1000, 10000, 5, -1000",
            "2000, 1000, 10000, 5, 0",
            "2000, 1000, 10000, 5, 9000"
    })
    public void shouldNotAddNegativeTests(int initialBalance, int minBalance, int maxBalance, int rate, int amount) {
        SavingAccount account = new SavingAccount(
                initialBalance,
                minBalance,
                maxBalance,
                rate
        );

        boolean result = account.add(amount);

        Assertions.assertAll(
                () -> assertEquals(initialBalance, account.getBalance()),
                () -> assertFalse(result)
        );
    }

    @Test
    public void shouldGivePercent() {
        SavingAccount account = new SavingAccount(
                200,
                0,
                10_000,
                15
        );

        Assertions.assertEquals(30, account.yearChange());
    }

    @Test
    public void shouldGivePercentRoundingValueTest() {
        SavingAccount account = new SavingAccount(
                99,
                0,
                10_000,
                80
        );

        Assertions.assertEquals(79, account.yearChange());
    }
}
