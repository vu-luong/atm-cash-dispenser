package com.vuluong.atmcashdispenser.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class SettingsUpdateRequest {

    private Map<String, Integer> amountByCash;
    private int threshold;
}
