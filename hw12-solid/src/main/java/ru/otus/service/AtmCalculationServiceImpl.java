package ru.otus.service;

import java.util.EnumMap;
import java.util.Map;
import ru.otus.storage.Denomination;

public class AtmCalculationServiceImpl implements AtmCalculationService {

    @Override
    public int calculateTotalAmount(Map<Denomination, Integer> banknotes) {
        return banknotes.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getValue() * entry.getValue())
                .sum();
    }

    @Override
    public Map<Denomination, Integer> calculateWithdrawal(Map<Denomination, Integer> banknotes, int amount) {
        Map<Denomination, Integer> tempBanknotes = new EnumMap<>(banknotes);
        Map<Denomination, Integer> result = new EnumMap<>(Denomination.class);
        int remaining = amount;

        for (Denomination denomination : Denomination.values()) {
            int denominationValue = denomination.getValue();
            int count = tempBanknotes.getOrDefault(denomination, 0);

            if (denominationValue <= remaining && count > 0) {
                int needed = remaining / denominationValue;
                int toTake = Math.min(needed, count);

                tempBanknotes.put(denomination, count - toTake);
                result.put(denomination, toTake);
                remaining -= toTake * denominationValue;
            }
        }

        if (remaining > 0) {
            throw new IllegalArgumentException("Невозможно выдать запрашиваемую сумму: недостаточно средств.");
        }

        return result;
    }

    @Override
    public int calculateTotalAdded(Denomination denomination, int count) {
        return denomination.getValue() * count;
    }
}
