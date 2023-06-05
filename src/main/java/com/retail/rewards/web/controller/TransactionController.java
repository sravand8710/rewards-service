package com.retail.rewards.web.controller;

import com.retail.rewards.web.model.Transaction;
import com.retail.rewards.web.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<Transaction>> getTransactions(@RequestParam(required = false) final LocalDate startDate,
                                                             @RequestParam(required = false) final LocalDate endDate) {
        return ResponseEntity.ok(transactionService.getTransactions(startDate, endDate));
    }

}
