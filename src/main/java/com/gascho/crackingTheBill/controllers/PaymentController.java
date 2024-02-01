package com.gascho.crackingTheBill.controllers;

import com.gascho.crackingTheBill.dtos.PaymentTotals;
import com.gascho.crackingTheBill.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/crackThatBill")
    @Validated
    public PaymentTotals calculatePayment(
            @RequestParam @PositiveOrZero(message = "Os valores não devem ser negativos") List<Double> userValues,
            @RequestParam @PositiveOrZero(message = "Os valores não devem ser negativos") List<Double> friendValues,
            @RequestParam @PositiveOrZero(message = "O valor deve não deve ser negativo") double deliveryValue,
            @RequestParam @PositiveOrZero(message = "O valor deve não deve ser negativo") double descountValue,
            @RequestParam @NotNull boolean isPercentualDescount) {
        return this.paymentService.totalsCalculation(
                userValues, friendValues, deliveryValue, descountValue, isPercentualDescount
        );
    }
}
