package com.gascho.crackingTheBill.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import com.gascho.crackingTheBill.dtos.PaymentTotals;

public class PaymentService {

    public PaymentTotals totalsCalculation(List<Double> userOrders, List<Double> friendOrders, double deliveryValue,
                                  double descountValue, boolean isPercentualDescount) {
        BigDecimal totalUserOrderValue = sumValues(userOrders);
        BigDecimal totalFriendOrderValue = sumValues(friendOrders);

        BigDecimal totalBillBeforeDescount = totalUserOrderValue.add(totalFriendOrderValue);
        double calculatedDescountValue = isPercentualDescount ? descountValue * 100 / totalBillBeforeDescount.doubleValue() : descountValue;

        BigDecimal totalUserBill = totalUserOrderValue
                .add(calculateProportionalDelivery(totalUserOrderValue, deliveryValue, totalBillBeforeDescount))
                .subtract(calculateProportionalDescount(totalUserOrderValue, calculatedDescountValue, totalBillBeforeDescount));
        
        BigDecimal totalFriendBill = totalFriendOrderValue
                .add(calculateProportionalDelivery(totalFriendOrderValue, deliveryValue, totalBillBeforeDescount))
                .subtract(calculateProportionalDescount(totalFriendOrderValue, calculatedDescountValue, totalBillBeforeDescount));

        return new PaymentTotals(totalUserBill, totalFriendBill);
    }

    public BigDecimal sumValues(List<Double> valuesList) {
        return valuesList.stream().map(BigDecimal::valueOf).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateProportionalDelivery(BigDecimal orderValue, double deliveryValue, BigDecimal totalBillBeforeDescount) {
        return BigDecimal.valueOf(deliveryValue).multiply(orderValue).divide(totalBillBeforeDescount, 2, RoundingMode.HALF_EVEN);
    }

    private BigDecimal calculateProportionalDescount(BigDecimal orderValue, double calculatedDescountValue, BigDecimal totalBillBeforeDescount) {
        return BigDecimal.valueOf(calculatedDescountValue).multiply(orderValue).divide(totalBillBeforeDescount, 2, RoundingMode.HALF_EVEN);
    }

}
