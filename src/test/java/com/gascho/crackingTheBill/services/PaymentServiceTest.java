package com.gascho.crackingTheBill.services;

import com.gascho.crackingTheBill.dtos.PaymentTotals;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class PaymentServiceTest {

    @Test
    public void totalPaymentCalculation() {
        List<Double> userOrders = Arrays.asList(40.00,2.00);
        List<Double> friendOrders = List.of(8.00);

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

        assertThat(totals.getUserBill()).isEqualTo(BigDecimal.valueOf(31.92));
        assertThat(totals.getFriendBill()).isEqualTo(BigDecimal.valueOf(6.08));
    }
}
