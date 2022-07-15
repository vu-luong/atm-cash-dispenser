package com.vuluong.atmcashdispenser.controller;

import com.vuluong.atmcashdispenser.request.WithdrawalAmountRequest;
import com.vuluong.atmcashdispenser.service.CashService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Controller
@AllArgsConstructor
public class CashWithdrawalController {

    private final CashService cashService;

    @GetMapping("/")
    public String homeGet() {
        if (!cashService.isInitialized()) {
            return "redirect:/settings";
        } else {
            return "redirect:/dispense";
        }
    }

    @GetMapping("/withdrawal")
    public String withdrawalGet(Model model) {
        model.addAttribute("isInitialized", cashService.isInitialized());
        return "withdrawal";
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<?> withdrawalPost(
        @RequestBody WithdrawalAmountRequest request
    ) {
        Map<String, Integer> result = cashService.dispense(request.getAmount());
        return ResponseEntity.ok(result);
    }
}
