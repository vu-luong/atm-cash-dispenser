package com.vuluong.atmcashdispenser.repo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.vuluong.atmcashdispenser.constant.Constants.CASH_VALUES;

@Component
public class CashRepository {

    @Setter
    @Getter
    private boolean initialized = false;
    private static final Map<Double, Integer> amountByCash = new HashMap<>();

    public CashRepository() {
        // Initialize the amounts of all money values to be zero
        for (double cashValue : CASH_VALUES) {
            amountByCash.put(cashValue, 0);
        }
    }
}
