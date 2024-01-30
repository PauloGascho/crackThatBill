package com.gascho.crackingTheBill.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void calculationPaymentTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/crackThatBill")
                        .param("userValues", "40.00,2.00")
                        .param("friendValues","8.00")
                        .param("deliveryValue","8.00")
                        .param("descountValue","20.00")
                        .param("isPercentualDescount","false"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userBill").value(31.92))
                .andExpect(MockMvcResultMatchers.jsonPath("$.friendBill").value(6.08));
    }
}
