package ru.otus.atm;

import ru.otus.printer.AtmPrinter;
import ru.otus.service.AtmCalculationService;
import ru.otus.storage.CashStorage;
import ru.otus.storage.Denomination;

@SuppressWarnings("java:S112")
public class Atm {
    private final CashStorage storage;
    private final AtmPrinter printer;
    private final AtmCalculationService calculationService;

    public Atm(CashStorage storage, AtmPrinter printer, AtmCalculationService calculationService) {
        this.storage = storage;
        this.printer = printer;
        this.calculationService = calculationService;
    }

    public void deposit(Denomination denomination, int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("���������� ������� ������ ���� ������ ����!");
        }
        storage.addBanknotes(denomination, count);
        printer.printDeposit(calculationService.calculateTotalAdded(denomination, count), storage.getTotalAmount());
    }

    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new RuntimeException("Сумма должна быть больше 0!");
        }
        boolean success = storage.withdrawAmount(amount);
        if (!success) {
            throw new RuntimeException("Невозможно выдать запрошенную сумму!");
        }
        printer.printWithdraw(amount, storage.getTotalAmount());
    }

    public void printBalance() {
        printer.printBalance(storage.getTotalAmount());
    }
}
