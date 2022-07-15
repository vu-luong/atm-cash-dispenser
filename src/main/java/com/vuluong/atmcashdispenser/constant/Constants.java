package com.vuluong.atmcashdispenser.constant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constants {

    public static final List<String> CASH_AMOUNT_KEYS = Arrays.asList(
        "amount5Cents",
        "amount10Cents",
        "amount20Cents",
        "amount50Cents",
        "amount1Dollar",
        "amount2Dollars",
        "amount5Dollars",
        "amount10Dollars",
        "amount20Dollars",
        "amount50Dollars",
        "amount100Dollars"
    );

    public static final Map<String, Double> VALUE_BY_CASH_AMOUNT_KEY;

    static {
        VALUE_BY_CASH_AMOUNT_KEY = new HashMap<>();
        VALUE_BY_CASH_AMOUNT_KEY.put("amount5Cents", 0.05);
        VALUE_BY_CASH_AMOUNT_KEY.put("amount10Cents", 0.1);
        VALUE_BY_CASH_AMOUNT_KEY.put("amount20Cents", 0.2);
        VALUE_BY_CASH_AMOUNT_KEY.put("amount50Cents", 0.5);
        VALUE_BY_CASH_AMOUNT_KEY.put("amount1Dollar", 1.0);
        VALUE_BY_CASH_AMOUNT_KEY.put("amount2Dollars", 2.0);
        VALUE_BY_CASH_AMOUNT_KEY.put("amount5Dollars", 5.0);
        VALUE_BY_CASH_AMOUNT_KEY.put("amount10Dollars", 10.0);
        VALUE_BY_CASH_AMOUNT_KEY.put("amount20Dollars", 20.0);
        VALUE_BY_CASH_AMOUNT_KEY.put("amount50Dollars", 50.0);
        VALUE_BY_CASH_AMOUNT_KEY.put("amount100Dollars", 100.0);
    }

    private Constants() {}
}
