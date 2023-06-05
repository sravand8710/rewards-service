package com.retail.rewards.web.service;

import com.retail.rewards.web.factory.TestData;
import com.retail.rewards.web.repository.TransactionRepo;
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
class TransactionServiceImplTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private TransactionRepo transactionRepo;

    @Test
    void testGetTransactions() {
        when(transactionRepo.findByDateBetweenOrderByDateDesc(any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(List.of(TestData.getTransaction()));
        assertEquals(List.of(TestData.getTransaction()),
                transactionService.getTransactions(LocalDate.of(2023, 6, 1), LocalDate.now()));
        verify(transactionRepo).findByDateBetweenOrderByDateDesc(any(LocalDate.class), any(LocalDate.class));
    }

    @Test
    void testGetTransactionsByCustomerId() {
        when(transactionRepo.findByCustomerIdAndDateBetweenOrderByDateDesc(anyString(), any(LocalDate.class),
                any(LocalDate.class))).thenReturn(List.of(TestData.getTransaction()));
        assertEquals(List.of(TestData.getTransaction()), transactionService.getTransactionsByCustomer(
                "frederickford", LocalDate.of(2023, 6, 1), LocalDate.now()));
        verify(transactionRepo).findByCustomerIdAndDateBetweenOrderByDateDesc(anyString(), any(LocalDate.class),
                any(LocalDate.class));
    }

    @Test
    void testGetCustomerIdsWithTransactions() {
        when(transactionRepo.findCustomerIdsByDateBetween(any(LocalDate.class), any(LocalDate.class))).
                thenReturn(Set.of("frederickford"));
        assertEquals(Set.of("frederickford"), transactionService.
                getCustomerIdsWithTransactions(LocalDate.of(2023, 6, 1), LocalDate.now()));
        verify(transactionRepo).findCustomerIdsByDateBetween(any(LocalDate.class), any(LocalDate.class));
    }

}
