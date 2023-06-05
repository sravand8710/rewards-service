package com.retail.rewards.web.controller;

import com.retail.rewards.web.model.Rewards;
import com.retail.rewards.web.service.RewardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rewards")
public class RewardsController {

    private final RewardsService rewardsService;

    @GetMapping
    public ResponseEntity<List<Rewards>> getRewards(@RequestParam(required = false) final LocalDate startDate,
                                                    @RequestParam(required = false) final LocalDate endDate) {
        return ResponseEntity.ok(rewardsService.getRewards(startDate, endDate));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Rewards> getRewardsByCustomer(@PathVariable final String customerId,
                                                        @RequestParam(required = false) final LocalDate startDate,
                                                        @RequestParam(required = false) final LocalDate endDate) {
        return ResponseEntity.ok(rewardsService.getRewardsByCustomer(customerId, startDate, endDate));
    }

}
