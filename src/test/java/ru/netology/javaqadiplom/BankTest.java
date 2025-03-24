package ru.netology.javaqadiplom;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BankTest {

    @Test
    void shouldTransferSuccess() {
        Bank bank = new Bank();
        Account from = new SavingAccount(1000, 0, 2000, 5);
        Account to = new SavingAccount(500, 0, 1500, 5);

        boolean result = bank.transfer(from, to, 300);

        Assertions.assertTrue(result); // Операция должна пройти успешно
        Assertions.assertEquals(700, from.getBalance()); // Баланс from должен уменьшиться на 300
        Assertions.assertEquals(800, to.getBalance()); // Баланс to должен увеличиться на 300
    }

    @Test
    void shouldTransferInsufficientFunds() {
        Bank bank = new Bank();
        Account from = new SavingAccount(100, 0, 1000, 5);
        Account to = new SavingAccount(500, 0, 1500, 5);

        boolean result = bank.transfer(from, to, 300);

        Assertions.assertFalse(result); // Операция не должна пройти (недостаточно средств)
        Assertions.assertEquals(100, from.getBalance()); // Баланс from не должен измениться
        Assertions.assertEquals(500, to.getBalance()); // Баланс to не должен измениться
    }

    @Test
    void shouldTransferCannotReplenish() {
        Bank bank = new Bank();
        Account from = new SavingAccount(1000, 0, 2000, 5);
        Account to = new SavingAccount(800, 0, 1000, 5);

        boolean result = bank.transfer(from, to, 300);

        Assertions.assertFalse(result); // Операция не должна пройти (превышение maxBalance на счёте to)
        Assertions.assertEquals(1000, from.getBalance()); // Баланс from не должен измениться
        Assertions.assertEquals(800, to.getBalance()); // Баланс to не должен измениться
    }

    @Test
    void shouldTransferNegativeAmount() {
        Bank bank = new Bank();
        Account from = new SavingAccount(1000, 0, 2000, 5);
        Account to = new SavingAccount(500, 0, 1500, 5);

        boolean result = bank.transfer(from, to, -100);

        Assertions.assertFalse(result); // Операция не должна пройти (отрицательная сумма)
        Assertions.assertEquals(1000, from.getBalance()); // Баланс from не должен измениться
        Assertions.assertEquals(500, to.getBalance()); // Баланс to не должен измениться
    }

    @Test
    void shouldTransferZeroAmount() {
        Bank bank = new Bank();
        Account from = new SavingAccount(1000, 0, 2000, 5);
        Account to = new SavingAccount(500, 0, 1500, 5);

        boolean result = bank.transfer(from, to, 0);

        Assertions.assertFalse(result); // Операция не должна пройти (нулевая сумма)
        Assertions.assertEquals(1000, from.getBalance()); // Баланс from не должен измениться
        Assertions.assertEquals(500, to.getBalance()); // Баланс to не должен измениться
    }
}