package com.vuluong.atmcashdispenser.repo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.vuluong.atmcashdispenser.constant.Constants.CASH_AMOUNT_KEYS;

@Component
@Setter
@Getter
public class CashRepository {

    private boolean initialized = false;
    private Map<String, Integer> amountByCash = new HashMap<>();

    public CashRepository() {
        // Initialize the amounts of all cash values to be zero
        for (String cashAmountKey : CASH_AMOUNT_KEYS) {
            amountByCash.put(cashAmountKey, 0);
        }
    }

    public void setAmount(String cashAmountKey, int amount) {
        amountByCash.put(cashAmountKey, amount);
    }

    public void subtractAmount(String cashAmountKey, int amount) {
        int currentAmount = amountByCash.get(cashAmountKey);
        if (amount > currentAmount) {
            throw new IllegalArgumentException(
                "The given amount is too large!"
            );
        }
        amountByCash.put(cashAmountKey, currentAmount - amount);
    }

    public List<Map.Entry<String, Integer>> getPositiveSortedList() {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(
            amountByCash.entrySet()
        );
        list.sort(Map.Entry.comparingByValue());
        return list.stream()
            .filter(entry -> entry.getValue() > 0)
            .collect(Collectors.toList());
    }
}
