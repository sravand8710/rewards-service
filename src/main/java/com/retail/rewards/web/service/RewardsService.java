package com.retail.rewards.web.service;

import com.retail.rewards.web.model.Rewards;

import java.time.LocalDate;
import java.util.List;

public interface RewardsService {

    /**
     *
     * @param startDate
     * @param endDate
     * @return {@link List} of {@link Rewards} for all customerIds who have valid txs within the date range
     */
    List<Rewards> getRewards(final LocalDate startDate, final LocalDate endDate);

    /**
     *
     * @param customerId
     * @param startDate
     * @param endDate
     * @return {@link Rewards} for customerId with total & monthly points within the date range
     */
    Rewards getRewardsByCustomer(final String customerId, final LocalDate startDate, final LocalDate endDate);

}
