package ru.otus.printer;

public interface AtmPrinter {
    void printDeposit(int count, int balance);

    void printWithdraw(int amount, int balance);

    void printError(String message);

    void printBalance(int balance);
}
