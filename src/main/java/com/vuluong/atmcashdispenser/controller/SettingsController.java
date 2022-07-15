package com.vuluong.atmcashdispenser.controller;

import com.vuluong.atmcashdispenser.service.CashService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

@Controller
@AllArgsConstructor
public class SettingsController {

    private final CashService cashService;

    @GetMapping("/settings")
    public String settingsGet() {
        if (cashService.isInitialized()) {
            return "redirect:/settings/update";
        } else {
            return "redirect:/settings/initialization";
        }
    }

    @GetMapping("settings/initialization")
    public String settingsInitializationGet() {
        return "settings/init";
    }

    @PostMapping("settings/initialization")
    public ResponseEntity<String> settingsInitializationPost(
        @RequestBody HashMap<String, Integer> settingInitializationData
    ) {
        cashService.initialize(
            settingInitializationData
        );

        return ResponseEntity.ok()
            .build();
    }

    @GetMapping("settings/update")
    public String settingsUpdateGet(Model model) {
        model.addAttribute("amountByCash", cashService.getAmountByCash());
        return "settings/update";
    }
}