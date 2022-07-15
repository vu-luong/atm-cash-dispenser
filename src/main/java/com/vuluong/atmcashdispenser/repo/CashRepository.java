package com.vuluong.atmcashdispenser.repo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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
    
    public void addAmount(String cashAmountKey, int amount) {
        amountByCash.put(cashAmountKey, amount);
    }
}
