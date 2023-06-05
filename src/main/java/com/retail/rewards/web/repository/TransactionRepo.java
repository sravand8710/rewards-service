package com.retail.rewards.web.repository;

import com.retail.rewards.web.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    @Query(" SELECT DISTINCT t.customerId FROM transactions t WHERE t.date BETWEEN :startDate AND :endDate ")
    Set<String> findCustomerIdsByDateBetween(final LocalDate startDate, final LocalDate endDate);

    List<Transaction> findByCustomerIdAndDateBetweenOrderByDateDesc(final String customerId, final LocalDate startDate,
                                                                    final LocalDate endDate);

    List<Transaction> findByDateBetweenOrderByDateDesc(final LocalDate startDate, final LocalDate endDate);

}
