package ru.otus.printer;

@SuppressWarnings("java:S106")
public class AtmPrinterImpl implements AtmPrinter {
    @Override
    public void printDeposit(int totalAdded, int balance) {
        System.out.println("Внесено: " + totalAdded + " Баланс: " + balance);
    }

    @Override
    public void printWithdraw(int amount, int balance) {
        System.out.println("Выдано: " + amount + " Баланс: " + balance);
    }

    @Override
    public void printError(String message) {
        System.err.println("Ошибка: " + message);
    }

    @Override
    public void printBalance(int balance) {
        System.out.println("Текущий баланс: " + balance);
    }
}
