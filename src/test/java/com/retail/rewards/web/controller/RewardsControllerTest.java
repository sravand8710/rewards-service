package com.retail.rewards.web.controller;

import com.retail.rewards.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@ContextConfiguration(classes = Application.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RewardsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetRewards() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rewards")
                        .param("startDate", "2023-01-01")
                        .param("endDate", "2023-06-04")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(15))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].customerId").value("benniehorton"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].total").value(539));
    }

    @Test
    void testGetRewardsByCustomer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rewards/benniehorton")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").value("benniehorton"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.total").value(539));
    }

}
