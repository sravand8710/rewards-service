package com.retail.rewards.web.service;

import com.retail.rewards.web.model.MonthlyPoints;
import com.retail.rewards.web.model.Rewards;
import com.retail.rewards.web.model.Transaction;
import com.retail.rewards.web.utility.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RewardsServiceImpl implements RewardsService {

    private final TransactionServiceImpl transactionService;

    @Override
    public List<Rewards> getRewards(final LocalDate startDate, final LocalDate endDate) {
        final LocalDate[] startAndEndDates = DateUtil.getStartAndEndDates(startDate, endDate);
        log.info("Getting rewards for all customers between {} & {}", startAndEndDates[0], startAndEndDates[1]);
        // query all customerIds within date range & map customerId to rewards obj
        return transactionService.getCustomerIdsWithTransactions(startAndEndDates[0], startAndEndDates[1]).stream()
                .map(customerId -> calculatePoints(customerId,
                        transactionService.getTransactionsByCustomer(customerId, startAndEndDates[0],
                                startAndEndDates[1])))
                .collect(Collectors.toList());
    }

    @Override
    public Rewards getRewardsByCustomer(final String customerId, final LocalDate startDate, final LocalDate endDate) {
        final LocalDate[] startAndEndDates = DateUtil.getStartAndEndDates(startDate, endDate);
        log.info("Getting rewards for {} between {} & {}", customerId, startAndEndDates[0], startAndEndDates[1]);
        return calculatePoints(customerId, transactionService.getTransactionsByCustomer(customerId, startAndEndDates[0],
                startAndEndDates[1]));
    }


    /**
     *
     * @param customerId
     * @param transactions
     * @return {@link Rewards} for customerId with total & monthly points
     */
    private Rewards calculatePoints(final String customerId, final List<Transaction> transactions) {
        final Map<String, Integer> monthlyMap = new LinkedHashMap<>();
        int total = 0;
        for (final Transaction transaction : transactions) {
            final int amount = (int) transaction.getAmount(); //ignores decimal value, if any
            int points = 0;
            if (amount > 100) { //adds 2 points per $ spend above 100
                points += 2 * (amount - 100);
            }
            // adds 50 points if amount equals/exceeds 100, otherwise adds a point per $ spend between 50 & 100
            if (amount > 50) {
                points += Integer.min(amount, 100) - 50;
            }
            total += points;
            if (points > 0) { //update map only when points are accumulated for tx
                final String monthAndYear = DateUtil.getMonthAndYear(transaction.getDate());
                monthlyMap.put(monthAndYear, monthlyMap.getOrDefault(monthAndYear, 0) + points);
            }
        }
        final List<MonthlyPoints> monthly = monthlyMap.entrySet().stream()
                .map(entry -> new MonthlyPoints(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return new Rewards(customerId, total, monthly);
    }

}
