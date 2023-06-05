package com.retail.rewards.web.service;

import com.retail.rewards.web.factory.TestData;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class RewardsServiceImplTest {

    @InjectMocks
    private RewardsServiceImpl rewardsService;

    @Mock
    private TransactionServiceImpl transactionService;

    @Test
    void testGetRewards() {
        when(transactionService.getCustomerIdsWithTransactions(any(LocalDate.class), any(LocalDate.class))).
                thenReturn(Set.of("frederickford"));
        when(transactionService.getTransactionsByCustomer(anyString(), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(List.of(TestData.getTransaction()));
        assertEquals(List.of(TestData.getRewards()), rewardsService.getRewards(
                LocalDate.of(2023, 6, 1), LocalDate.now()));
        verify(transactionService).getCustomerIdsWithTransactions(any(LocalDate.class), any(LocalDate.class));
        verify(transactionService).getTransactionsByCustomer(anyString(), any(LocalDate.class), any(LocalDate.class));
    }

    @Test
    void testGetRewardsByCustomer() {
        when(transactionService.getTransactionsByCustomer(anyString(), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(List.of(TestData.getTransaction()));
        assertEquals(TestData.getRewards(), rewardsService.getRewardsByCustomer("frederickford",
                LocalDate.of(2023, 6, 1), LocalDate.now()));
        verify(transactionService).getTransactionsByCustomer(anyString(), any(LocalDate.class), any(LocalDate.class));
    }

}
