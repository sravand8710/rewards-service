package com.retail.rewards.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MonthlyPoints {

    private String month;
    private int points;

}
