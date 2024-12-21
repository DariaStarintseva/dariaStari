package ru.otus;

import ru.otus.atm.Atm;
import ru.otus.printer.AtmPrinter;
import ru.otus.printer.AtmPrinterImpl;
import ru.otus.service.AtmCalculationService;
import ru.otus.service.AtmCalculationServiceImpl;
import ru.otus.storage.CashStorage;
import ru.otus.storage.Denomination;

public class Main {
    public static void main(String[] args) {
        AtmPrinter printer = new AtmPrinterImpl();
        AtmCalculationService calculationService = new AtmCalculationServiceImpl();
        CashStorage storage = new CashStorage(calculationService);
        Atm atm = new Atm(storage, printer, calculationService);

        atm.deposit(Denomination.FIVE_THOUSAND, 2);
        atm.withdraw(5000);
        atm.deposit(Denomination.HUNDRED, 5);
        atm.deposit(Denomination.FIVE_HUNDRED, 1);
        atm.deposit(Denomination.THOUSAND, 7);
        atm.deposit(Denomination.TWO_HUNDRED, 4);
        atm.printBalance();
        atm.withdraw(517);
    }
}
