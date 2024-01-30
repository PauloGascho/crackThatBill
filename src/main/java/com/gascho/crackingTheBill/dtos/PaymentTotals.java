package com.gascho.crackingTheBill.dtos;

import java.math.BigDecimal;

public class PaymentTotals {

    private final BigDecimal userBill;
    private final BigDecimal friendBill;


    public PaymentTotals(BigDecimal userBill, BigDecimal friendBill) {
        this.userBill = userBill;
        this.friendBill = friendBill;
    }

    public BigDecimal getUserBill() {
        return userBill;
    }

    public BigDecimal getFriendBill() {
        return friendBill;
    }
}
