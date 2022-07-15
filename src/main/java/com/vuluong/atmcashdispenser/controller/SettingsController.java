package com.vuluong.atmcashdispenser.controller;

import com.vuluong.atmcashdispenser.request.SettingsUpdateRequest;
import com.vuluong.atmcashdispenser.service.CashService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

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

    @GetMapping("/settings/initialization")
    public String settingsInitializationGet() {
        return "settings/init";
    }

    @PostMapping("/settings/initialization")
    public ResponseEntity<String> settingsInitializationPost(
        @RequestBody Map<String, Integer> requestBody
    ) {
        cashService.initialize(
            requestBody
        );

        return ResponseEntity.ok()
            .build();
    }

    @GetMapping("/settings/update")
    public String settingsUpdateGet(Model model) {
        model.addAttribute("isInitialized", cashService.isInitialized());
        model.addAttribute("amountByCash", cashService.getAmountByCash());
        model.addAttribute("warningThreshold", cashService.getWarningThreshold());
        return "settings/update";
    }

    @PutMapping("/settings/update")
    public ResponseEntity<?> settingsUpdatePut(
        @RequestBody SettingsUpdateRequest requestBody
    ) {
        cashService.updateCashAmount(requestBody.getAmountByCash());
        cashService.updateWarningThreshold(requestBody.getThreshold());
        return ResponseEntity.ok()
            .build();
    }
}
