package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
                5_000,
                15
        );

        Bank bank = new Bank();

        boolean result = bank.transfer(credit, saving, 6_000);

        Assertions.assertAll(
                () -> assertEquals(7_000, saving.getBalance()),
                () -> assertEquals(0, credit.getBalance()),
                () -> assertFalse(result)
        );
    }
}
