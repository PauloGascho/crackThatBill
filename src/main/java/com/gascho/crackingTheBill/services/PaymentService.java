package com.gascho.crackingTheBill.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import com.gascho.crackingTheBill.dtos.PaymentTotals;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class PaymentService {

    public PaymentTotals totalsCalculation(List<Double> userOrders, List<Double> friendOrders, double deliveryValue,
                                  double descountValue, boolean isPercentualDescount) {
        BigDecimal totalUserOrderValue = sumValues(userOrders);
        BigDecimal totalFriendOrderValue = sumValues(friendOrders);

        BigDecimal totalBillBeforeDescount = totalUserOrderValue.add(totalFriendOrderValue);
        double calculatedDescountValue = isPercentualDescount ? descountValue * 100 / totalBillBeforeDescount.doubleValue() : descountValue;

        BigDecimal totalUserBill = calculateUserBill(totalUserOrderValue, deliveryValue, calculatedDescountValue, totalBillBeforeDescount);
        BigDecimal totalFriendBill = calculateUserBill(totalFriendOrderValue, deliveryValue, calculatedDescountValue, totalBillBeforeDescount);

        return new PaymentTotals(totalUserBill, totalFriendBill);
    }

    public BigDecimal sumValues(List<Double> valuesList) {
        return valuesList.stream().map(BigDecimal::valueOf).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateProportionalValue(BigDecimal orderValue, double deliveryOrDescountValue, BigDecimal totalBillBeforeDescount) {
        return BigDecimal.valueOf(deliveryOrDescountValue).multiply(orderValue).divide(totalBillBeforeDescount, 2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal calculateUserBill(BigDecimal totalUserOrFriendOrderValue, double deliveryValue, double calculatedDescountValue, BigDecimal totalBillBeforeDescount) {
        return totalUserOrFriendOrderValue
                .add(calculateProportionalValue(totalUserOrFriendOrderValue, deliveryValue, totalBillBeforeDescount))
                .subtract(calculateProportionalValue(totalUserOrFriendOrderValue, calculatedDescountValue, totalBillBeforeDescount));
    }

}
