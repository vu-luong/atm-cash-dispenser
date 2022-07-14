package com.vuluong.atmcashdispenser.controller;

import com.vuluong.atmcashdispenser.service.CashService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class CashDispenseController {

    private final CashService cashService;

    @GetMapping("/")
    public String homeGet() {
        if (!cashService.isInitialized()) {
            return "redirect:/initialize";
        } else {
            return "redirect:/dispense";
        }
    }

    @GetMapping("/initialize")
    public String initializeGet() {
        if (cashService.isInitialized()) {
            return "redirect:/dispense";
        } else {
            return "initialize";
        }
    }
}
