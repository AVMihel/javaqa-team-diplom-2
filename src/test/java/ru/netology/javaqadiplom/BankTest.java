package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class BankTest {

    @Test
    public void shouldTransferFromCreditToSaving() {
        Account saving = new SavingAccount(
                0,
                0,
                10_000,
                5
        );

        Account credit = new CreditAccount(
                0,
                10_000,
                15
        );

        Bank bank = new Bank();

        boolean result = bank.transfer(credit, saving, 5_000);

        Assertions.assertAll(
                () -> assertEquals(5_000, saving.getBalance()),
                () -> assertEquals(-5_000, credit.getBalance()),
                () -> assertTrue(result)
        );
    }

    @Test
    public void shouldTransferFromSavingToCredit() {
        Account saving = new SavingAccount(
                6_000,
                0,
                10_000,
                5
        );

        Account credit = new CreditAccount(
                0,
                10_000,
                15
        );

        Bank bank = new Bank();

        boolean result = bank.transfer(saving, credit, 5_000);

        Assertions.assertAll(
                () -> assertEquals(1_000, saving.getBalance()),
                () -> assertEquals(5_000, credit.getBalance()),
                () -> assertTrue(result)
        );
    }

    @Test
    public void shouldNotTransferFromCreditToSavingAmountZero() {
        Account saving = new SavingAccount(
                0,
                0,
                10_000,
                5
        );

        Account credit = new CreditAccount(
                0,
                10_000,
                15
        );

        Bank bank = new Bank();

        boolean result = bank.transfer(credit, saving, 0);

        Assertions.assertAll(
                () -> assertEquals(0, saving.getBalance()),
                () -> assertEquals(0, credit.getBalance()),
                () -> assertFalse(result)
        );
    }

    @Test
    public void shouldNotTransferFromCreditToSavingAmountNegative() {
        Account saving = new SavingAccount(
                0,
                0,
                10_000,
                5
        );

        Account credit = new CreditAccount(
                0,
                10_000,
                15
        );

        Bank bank = new Bank();

        boolean result = bank.transfer(credit, saving, -1_000);

        Assertions.assertAll(
                () -> assertEquals(0, saving.getBalance()),
                () -> assertEquals(0, credit.getBalance()),
                () -> assertFalse(result)
        );
    }

    @Test
    public void shouldNotTransferFromSavingToCreditAmountZero() {
        Account saving = new SavingAccount(
                2_000,
                0,
                10_000,
                5
        );

        Account credit = new CreditAccount(
                0,
                10_000,
                15
        );

        Bank bank = new Bank();

        boolean result = bank.transfer(saving, credit, 0);

        Assertions.assertAll(
                () -> assertEquals(2_000, saving.getBalance()),
                () -> assertEquals(0, credit.getBalance()),
                () -> assertFalse(result)
        );
    }

    @Test
    public void shouldNotTransferFromSavingToCreditAmountNegative() {
        Account saving = new SavingAccount(
                2_000,
                0,
                10_000,
                5
        );

        Account credit = new CreditAccount(
                0,
                10_000,
                15
        );

        Bank bank = new Bank();

        boolean result = bank.transfer(saving, credit, -1_000);

        Assertions.assertAll(
                () -> assertEquals(2_000, saving.getBalance()),
                () -> assertEquals(0, credit.getBalance()),
                () -> assertFalse(result)
        );
    }

    @Test
    public void shouldNotTransferFromCreditToSavingBalanceAboveMax() {
        Account saving = new SavingAccount(
                6_000,
                0,
                10_000,
                5
        );

        Account credit = new CreditAccount(
                0,
                10_000,
                15
        );

        Bank bank = new Bank();

        boolean result = bank.transfer(credit, saving, 5_000);

        Assertions.assertAll(
                () -> assertEquals(6_000, saving.getBalance()),
                () -> assertEquals(0, credit.getBalance()),
                () -> assertFalse(result)
        );
    }

    @Test
    public void shouldNotTransferFromCreditToSavingAboveCreditLimit() {
        Account saving = new SavingAccount(
                0,
                0,
                10_000,
                5
        );

        Account credit = new CreditAccount(
                0,
                5_000,
                15
        );

        Bank bank = new Bank();

        boolean result = bank.transfer(credit, saving, 6_000);

        Assertions.assertAll(
                () -> assertEquals(0, saving.getBalance()),
                () -> assertEquals(0, credit.getBalance()),
                () -> assertFalse(result)
        );
    }

    @Test
    public void shouldNotTransferFromSavingToCreditBalanceLessMin() {
        Account saving = new SavingAccount(
                7_000,
                2_000,
                10_000,
                5
        );

        Account credit = new CreditAccount(
                0,
                7_000,
                15
        );

        Bank bank = new Bank();

        boolean result = bank.transfer(saving, credit, 6_000);

        Assertions.assertAll(
                () -> assertEquals(7_000, saving.getBalance()),
                () -> assertEquals(0, credit.getBalance()),
                () -> assertFalse(result)
        );
    }

    @Test
    public void shouldTransferFromSavingToSaving() {
        Account saving1 = new SavingAccount(
                5_000,
                0_000,
                10_000,
                5
        );

        Account saving2 = new SavingAccount(
                0,
                0,
                10_000,
                5
        );

        Bank bank = new Bank();

        boolean result = bank.transfer(saving1, saving2, 5_000);

        Assertions.assertAll(
                () -> assertEquals(0, saving1.getBalance()),
                () -> assertEquals(5_000, saving2.getBalance()),
                () -> assertTrue(result)
        );
    }

    @ParameterizedTest
    @CsvSource({
            "0, 6000",
            "2000,1000"
    })
    public void shouldNotTransferFromSavingToSavingNegativeTests(int minBalance1, int initialBalance2) {
        Account saving1 = new SavingAccount(
                6_000,
                minBalance1,
                10_000,
                5
        );

        Account saving2 = new SavingAccount(
                initialBalance2,
                0,
                10_000,
                5
        );

        Bank bank = new Bank();

        boolean result = bank.transfer(saving1, saving2, 5_000);

        Assertions.assertAll(
                () -> assertEquals(6_000, saving1.getBalance()),
                () -> assertEquals(initialBalance2, saving2.getBalance()),
                () -> assertFalse(result)
        );
    }

    @Test
    public void shouldTransferFromCreditToCredit() {
        Account credit1 = new CreditAccount(
                1_000,
                2_000,
                15
        );

        Account credit2 = new CreditAccount(
                0,
                5_000,
                15
        );

        Bank bank = new Bank();

        boolean result = bank.transfer(credit1, credit2, 1_000);

        Assertions.assertAll(
                () -> assertEquals(0, credit1.getBalance()),
                () -> assertEquals(1_000, credit2.getBalance()),
                () -> assertTrue(result)
        );
    }

    @Test
    public void shouldNotTransferFromCreditToCreditResultBalanceLessCreditLimit() {
        Account credit1 = new CreditAccount(
                0,
                5_000,
                15
        );

        Account credit2 = new CreditAccount(
                0,
                5_000,
                15
        );

        Bank bank = new Bank();

        credit1.pay(4_000);

        boolean result = bank.transfer(credit1, credit2, 2_000);

        Assertions.assertAll(
                () -> assertEquals(-4_000, credit1.getBalance()),
                () -> assertEquals(0, credit2.getBalance()),
                () -> assertFalse(result)
        );
    }
}
