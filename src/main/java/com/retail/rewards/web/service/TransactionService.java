package com.retail.rewards.web.service;

import com.retail.rewards.web.model.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {

    List<Transaction> getTransactions(final LocalDate startDate, final LocalDate endDate);

}
