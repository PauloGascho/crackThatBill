package com.gascho.crackingTheBill.controllers;

import com.gascho.crackingTheBill.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(PaymentController.class)
@ContextConfiguration(classes = AppConfig.class)
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void calculationPaymentTest_success() throws Exception {
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

    @Test
    public void calculationPaymentTestIsPercentualValue_sucess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/crackThatBill")
                        .param("userValues", "40.00,2.00")
                        .param("friendValues","8.00")
                        .param("deliveryValue","8.00")
                        .param("descountValue","20.00")
                        .param("isPercentualDescount","true"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userBill").value(15.12))
                .andExpect(MockMvcResultMatchers.jsonPath("$.friendBill").value(2.88));
    }
}
