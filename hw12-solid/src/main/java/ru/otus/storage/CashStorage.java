package ru.otus.storage;

import java.util.EnumMap;
import java.util.Map;
import ru.otus.service.AtmCalculationService;

public class CashStorage {

    private final Map<Denomination, Integer> banknotes = new EnumMap<>(Denomination.class);
    private final AtmCalculationService calculationService;

    public CashStorage(AtmCalculationService calculationService) {
        this.calculationService = calculationService;
    }

    public void addBanknotes(Denomination denomination, int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Количество банкнот должно быть больше нуля!");
        }
        banknotes.put(denomination, banknotes.getOrDefault(denomination, 0) + count);
    }

    public boolean withdrawAmount(int amount) {
        try {
            Map<Denomination, Integer> withdrawal = calculationService.calculateWithdrawal(banknotes, amount);

            withdrawal.forEach(
                    (denomination, count) -> banknotes.put(denomination, banknotes.get(denomination) - count));

            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public int getTotalAmount() {
        return calculationService.calculateTotalAmount(banknotes);
    }

    public Map<Denomination, Integer> getState() {
        return new EnumMap<>(banknotes);
    }
}
