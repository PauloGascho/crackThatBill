package com.gascho.crackingTheBill.services;

import com.gascho.crackingTheBill.dtos.PaymentTotals;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

public class PaymentServiceTest {

    @Test
    public void totalPaymentCalculation_success() {
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

        assertThat(totals.getUserBill()).isEqualTo(BigDecimal.valueOf(31.92).setScale(2, RoundingMode.HALF_EVEN));
        assertThat(totals.getFriendBill()).isEqualTo(BigDecimal.valueOf(6.08).setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    public void totalPaymentCalculationIsPercentualDescount_success() {
        List<Double> userOrders = Arrays.asList(40.00,2.00);
        List<Double> friendOrders = List.of(8.00);

        double deliveryValue = 8.00;
        double descountValue = 20.00;

        boolean isPercentualDescount = true;

        PaymentService paymentService = new PaymentService();
        PaymentTotals totals = paymentService.totalsCalculation(
                userOrders,
                friendOrders,
                deliveryValue,
                descountValue,
                isPercentualDescount
        );

        assertThat(totals.getUserBill()).isEqualTo(BigDecimal.valueOf(15.12).setScale(2, RoundingMode.HALF_EVEN));
        assertThat(totals.getFriendBill()).isEqualTo(BigDecimal.valueOf(2.88).setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    public void calculateProportionalDelivery_success() {
        BigDecimal orderValue = BigDecimal.valueOf(42);
        double deliveryValue = 8.00;
        BigDecimal totalBillBeforeDescount = BigDecimal.valueOf(50);

        PaymentService paymentService = new PaymentService();
        BigDecimal calculatedDelivery = paymentService.calculateProportionalValue(orderValue, deliveryValue, totalBillBeforeDescount);

        assertThat(calculatedDelivery).isEqualTo(BigDecimal.valueOf(6.72).setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    public void calculateProportionalDescountValue_success() {
        BigDecimal orderValue = BigDecimal.valueOf(42);
        double calculatedDescountValue = 20.00;
        BigDecimal totalBillBeforeDescount = BigDecimal.valueOf(50);

        PaymentService paymentService = new PaymentService();
        BigDecimal calculatedDelivery = paymentService.calculateProportionalValue(orderValue, calculatedDescountValue, totalBillBeforeDescount);

        assertThat(calculatedDelivery).isEqualTo(BigDecimal.valueOf(16.8).setScale(2, RoundingMode.HALF_EVEN));
    }

    @Test
    public void calculateUserBill_sucess() {
        BigDecimal userOrderValue = BigDecimal.valueOf(42);
        double deliveryValue = 8.00;
        double calculatedDescountValue = 20.00;
        BigDecimal totalBillBeforeDescount = BigDecimal.valueOf(50);

        PaymentService paymentService = new PaymentService();
        BigDecimal calculatedBill = paymentService.calculateUserBill(userOrderValue, deliveryValue, calculatedDescountValue, totalBillBeforeDescount);

        assertThat(calculatedBill).isEqualTo(BigDecimal.valueOf(31.92).setScale(2, RoundingMode.HALF_EVEN));
    }

}
