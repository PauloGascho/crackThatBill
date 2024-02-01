package com.gascho.crackingTheBill.controllers;

import com.gascho.crackingTheBill.dtos.PaymentTotals;
import com.gascho.crackingTheBill.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public PaymentTotals calculatePayment(
            @RequestParam List<Double> userValues,
            @RequestParam List<Double> friendValues,
            @RequestParam double deliveryValue,
            @RequestParam double descountValue,
            @RequestParam boolean isPercentualDescount) {
        return this.paymentService.totalsCalculation(
                userValues, friendValues, deliveryValue, descountValue, isPercentualDescount
        );
    }
}
