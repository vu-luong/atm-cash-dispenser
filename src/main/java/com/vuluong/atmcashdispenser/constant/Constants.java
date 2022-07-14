package com.vuluong.atmcashdispenser.constant;

import java.util.Arrays;
import java.util.List;

public class Constants {

    public static List<Double> CASH_VALUES = Arrays.asList(
        0.05,       // 5 cents
        0.1,        // 10 cents
        0.2,        // 20 cents
        0.5,        // 50 cents
        1.0,        // 1 dollar
        2.0,        // 2 dollars
        5.0,        // 5 dollars
        10.0,       // 10 dollars
        20.0,       // 20 dollars
        50.0,       // 50 dollars
        100.0       // 100 dollars
    );

    private Constants() {}
}
