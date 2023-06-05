package com.retail.rewards.web.utility;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;

import static org.apache.logging.log4j.util.Chars.SPACE;

@UtilityClass
public class DateUtil {

    /**
     *
     * @param startDate
     * @param endDate
     * @return startDate & endDate in an array as LocalDate[]{startDate, endDate} after validation
     */
    public LocalDate[] getStartAndEndDates(LocalDate startDate, LocalDate endDate) {
        final LocalDate now = LocalDate.now();
        if (endDate != null && endDate.isAfter(now)) { //validate endDate
            throw new IllegalArgumentException("endDate cannot be greater than the current date");
        }
        if (Objects.isNull(endDate)) {
            endDate = now;
        }
        if (startDate != null && startDate.isAfter(endDate)) { //validate startDate
            throw new IllegalArgumentException("startDate cannot be greater than endDate");
        }
        if (Objects.isNull(startDate)) {
            startDate = endDate.minusMonths(3); //default to 3 months
        }
        return new LocalDate[]{startDate, endDate};
    }

    /**
     *
     * @param localDate
     * @return month & year as String, example : June 2023 for 2023-06-03
     */
    public String getMonthAndYear(final LocalDate localDate) {
        return localDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH) + SPACE + localDate.getYear();
    }

}
