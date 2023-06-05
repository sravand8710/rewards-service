package com.retail.rewards.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Rewards {

    private String customerId;
    private int total;
    private List<MonthlyPoints> monthly;

}
