package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreditAccountTest {

    // Проверка корректного создания объекта
    @Test
    void shouldConstructorValidParameters() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        Assertions.assertEquals(1_000, account.getBalance());
        Assertions.assertEquals(5_000, account.getCreditLimit());
        Assertions.assertEquals(15, account.getRate());
    }

    // Проверка успешного пополнения счёта при нулевом балансе
    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    // Проверка успешного пополнения счёта при ненулевом балансе
    @Test
    void shouldAddValidAmount() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.add(1_500);

        Assertions.assertEquals(2_500, account.getBalance());
    }

    // Проверка на исключение при отрицательной ставке
    @Test
    void shouldConstructorInvalidRate() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(
                    1_000,
                    5_000,
                    -5
            );
        });

        Assertions.assertEquals("Накопительная ставка не может быть отрицательной, а у вас: -5", exception.getMessage());
    }

    // Проверка успешной операции оплаты
    @Test
    void shouldPayValidAmount() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(700);

        Assertions.assertEquals(300, account.getBalance());
    }

    // Проверка оплаты, превышающей кредитный лимит
    @Test
    void shouldNotPayExceedingCreditLimit() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(7_000);

        Assertions.assertEquals(1_000, account.getBalance()); // Баланс не должен измениться
    }

    // Проверка оплаты с некорректной суммой (отрицательная)
    @Test
    void shouldNotPayNegativAmount() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(-100);

        Assertions.assertEquals(1_000, account.getBalance()); // Баланс не должен измениться
    }

    // Проверка оплаты с некорректной суммой (нулевая)
    @Test
    void shouldNotPayInvalidAmount() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.pay(0);

        Assertions.assertEquals(1_000, account.getBalance()); // Баланс не должен измениться
    }

    // Проверка пополнения счёта на некорректную сумму (отрицательная)
    @Test
    void shouldNotAddNegativeAmount() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.add(-100);

        Assertions.assertEquals(1_000, account.getBalance()); // Баланс не должен измениться
    }

    // Проверка пополнения счёта на некорректную сумму (нулевая)
    @Test
    void shouldNotAddInvalidAmount() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.add(0);

        Assertions.assertEquals(1_000, account.getBalance()); // Баланс не должен измениться
    }

    // Проверка расчёта процентов при положительном балансе
    @Test
    void shouldNoInterestChargedPositiveBalance() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        Assertions.assertEquals(0, account.yearChange()); // Проценты не начисляются при положительном балансе
    }

    // Проверка расчёта процентов при отрицательном балансе
    @Test
    void shouldNoInterestChargedNegativeBalance() {
        CreditAccount account = new CreditAccount(
                -200,
                5_000,
                15
        );

        Assertions.assertEquals(-30, account.yearChange()); // 15% от -200 = -30
    }

    // Проверка расчёта процентов при нулевом балансе
    @Test
    void shouldInterestAccruedZeroBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        Assertions.assertEquals(0, account.yearChange()); // Проценты не начисляются при нулевом балансе
    }

    // Проверка на исключение при отрицательном начальном балансе
    @Test
    void shouldConstructorNegativeBalance() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(
                    -1_000,
                    5_000,
                    15
            );
        });

        Assertions.assertEquals("Начальный баланс не может быть отрицательным, а у вас: -1000", exception.getMessage());
    }


    // Проверка на исключение при отрицательном лимите
    @Test
    void shouldConstructorInvalidCreditLimit() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(
                    1_000,
                    -5_000,
                    15
            );
        });

        Assertions.assertEquals("Кредитный лимит не может быть отрицательным, а у вас: -5000", exception.getMessage());
    }
}
