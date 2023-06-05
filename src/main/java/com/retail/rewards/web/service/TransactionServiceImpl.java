package com.retail.rewards.web.service;

import com.retail.rewards.web.model.Transaction;
import com.retail.rewards.web.repository.TransactionRepo;
import com.retail.rewards.web.utility.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepo transactionRepo;

    @Override
    public List<Transaction> getTransactions(final LocalDate startDate, final LocalDate endDate) {
        final LocalDate[] startAndEndDates = DateUtil.getStartAndEndDates(startDate, endDate);
        log.info("Getting all transactions between {} & {}", startAndEndDates[0], startAndEndDates[1]);
        return transactionRepo.findByDateBetweenOrderByDateDesc(startAndEndDates[0], startAndEndDates[1]);
    }

    /**
     * @param customerId
     * @param startDate
     * @param endDate
     * @return {@link List} of {@link Transaction}s by customerId within the date range
     */
    public List<Transaction> getTransactionsByCustomer(final String customerId, final LocalDate startDate,
                                                       final LocalDate endDate) {
        final LocalDate[] startAndEndDates = DateUtil.getStartAndEndDates(startDate, endDate);
        log.info("Getting transactions for {} between {} & {}", customerId, startAndEndDates[0], startAndEndDates[1]);
        return transactionRepo.findByCustomerIdAndDateBetweenOrderByDateDesc(customerId, startAndEndDates[0],
                startAndEndDates[1]);
    }

    /**
     * @param startDate
     * @param endDate
     * @return {@link Set} of customerIds with transactions within the date range
     */
    public Set<String> getCustomerIdsWithTransactions(final LocalDate startDate, final LocalDate endDate) {
        final LocalDate[] startAndEndDates = DateUtil.getStartAndEndDates(startDate, endDate);
        log.info("Getting customer IDs with transactions between {} & {}", startAndEndDates[0], startAndEndDates[1]);
        return transactionRepo.findCustomerIdsByDateBetween(startAndEndDates[0], startAndEndDates[1]);
    }

}
