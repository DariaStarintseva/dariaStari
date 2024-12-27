package ru.otus.service;

import java.util.Map;
import ru.otus.storage.Denomination;

public interface AtmCalculationService {
    int calculateTotalAmount(Map<Denomination, Integer> banknotes);

    Map<Denomination, Integer> calculateWithdrawal(Map<Denomination, Integer> banknotes, int amount);

    int calculateTotalAdded(Denomination denomination, int count);
}
