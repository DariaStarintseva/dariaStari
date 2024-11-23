package homework;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class CustomerService {

    private final NavigableMap<Customer, String> customersMap =
            new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        return copyEntry(customersMap.firstEntry());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return copyEntry(customersMap.higherEntry(customer));
    }

    public void add(Customer customer, String data) {
        customersMap.put(customer, data);
    }

    private Map.Entry<Customer, String> copyEntry(Map.Entry<Customer, String> entry) {
        if (entry == null) {
            return null;
        }
        Customer copyCustomer = new Customer(
                entry.getKey().getId(), entry.getKey().getName(), entry.getKey().getScores());
        return new AbstractMap.SimpleEntry<>(copyCustomer, entry.getValue());
    }
}
