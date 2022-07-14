package com.vuluong.atmcashdispenser.service;

import com.vuluong.atmcashdispenser.repo.CashRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CashService {

    private final CashRepository cashRepository;

    public boolean isInitialized() {
        return cashRepository.isInitialized();
    }

    public void initialize() {
       
    }
}
