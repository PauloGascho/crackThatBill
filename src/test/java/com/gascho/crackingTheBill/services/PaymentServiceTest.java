package com.gascho.crackingTheBill.services;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class PaymentServiceTest {

    @Test
    public void totalPaymentCalculation() {
        List<Double> userOrders = Arrays.asList(40.00,2.00);
        List<Double> friendOrders = Arrays.asList(8.00);

        double deliveryValue = 8.00;
        double descountValue = 20.00;

        boolean isPercentualDescount = false;

        PaymentService paymentService = new PaymentService();

        PaymentTotals totals = paymentService.totalsCalculation(
                userOrders,
                friendOrders,
                deliveryValue,
                descountValue,
                isPercentualDescount
        );

        asserEquals(31.92, totals.getUserBill());
        assertEquals(6.08, totals.getFriendBill());
    }
}
