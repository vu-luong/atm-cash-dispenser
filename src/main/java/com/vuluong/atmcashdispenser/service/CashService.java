package com.vuluong.atmcashdispenser.service;

import com.vuluong.atmcashdispenser.repo.CashRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class CashService {

    private final CashRepository cashRepository;

    public boolean isInitialized() {
        return cashRepository.isInitialized();
    }

    public void initialize(HashMap<String, Integer> data) {
        data.forEach(cashRepository::addAmount);
        cashRepository.setInitialized(true);
    }

    public Map<String, Integer> getAmountByCash() {
        return cashRepository.getAmountByCash();
    }
}
