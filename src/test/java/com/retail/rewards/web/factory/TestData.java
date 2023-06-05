package com.retail.rewards.web.factory;

import com.retail.rewards.web.model.MonthlyPoints;
import com.retail.rewards.web.model.Rewards;
import com.retail.rewards.web.model.Transaction;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.List;

@UtilityClass
public class TestData {

    public Rewards getRewards() {
        return new Rewards("frederickford", 124, List.of(getMonthlyPoints()));
    }

    public MonthlyPoints getMonthlyPoints() {
        return new MonthlyPoints("June 2023", 124);
    }

    public Transaction getTransaction() {
        final Transaction transaction = new Transaction();
        transaction.setCustomerId("frederickford");
        transaction.setAmount(137.45);
        transaction.setDate(LocalDate.of(2023, 6, 2));
        return transaction;
    }

}
