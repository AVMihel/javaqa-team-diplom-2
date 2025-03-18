package ru.netology.javaqadiplom;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BankTest {

    @Test
    void shouldTransferSuccess() {
        Bank bank = new Bank();
        Account from = new Account();
        Account to = new Account();

        from.balance = 1000;
        to.balance = 500;

        boolean result = bank.transfer(from, to, 300);

        Assertions.assertTrue(result); // Операция должна пройти успешно
        Assertions.assertEquals(700, from.getBalance()); // Баланс должен измениться
        Assertions.assertEquals(800, to.getBalance()); // Баланс должен измениться
    }

    @Test
    void shouldTransferInsufficientFunds() {
        Bank bank = new Bank();
        Account from = new Account();
        Account to = new Account();

        from.balance = 100;
        to.balance = 500;

        boolean result = bank.transfer(from, to, 300);

        Assertions.assertFalse(result); // Операция не должна пройти
        Assertions.assertEquals(100, from.getBalance()); // Баланс не должен измениться
        Assertions.assertEquals(500, to.getBalance()); // Баланс не должен измениться
    }

    @Test
    void shouldTransferNegativeAmount() {
        Bank bank = new Bank();
        Account from = new Account();
        Account to = new Account();

        from.balance = 1000;
        to.balance = 500;

        boolean result = bank.transfer(from, to, -100);

        Assertions.assertFalse(result); // Операция не должна пройти
        Assertions.assertEquals(1000, from.getBalance()); // Баланс не должен измениться
        Assertions.assertEquals(500, to.getBalance()); // Баланс не должен измениться
    }

    @Test
    void shouldTransferZeroAmount() {
        Bank bank = new Bank();
        Account from = new Account();
        Account to = new Account();

        from.balance = 1000;
        to.balance = 500;

        boolean result = bank.transfer(from, to, 0);

        Assertions.assertFalse(result); // Операция не должна пройти
        Assertions.assertEquals(1000, from.getBalance()); // Баланс не должен измениться
        Assertions.assertEquals(500, to.getBalance()); // Баланс не должен измениться
    }
}