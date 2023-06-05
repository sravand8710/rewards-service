package com.retail.rewards.web.utility;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DateUtilTest {

    @Test
    void testGetStartAndEndDates() {
        // startDate and endDate are null, should default to 3 months ago and current date
        LocalDate startDate = null;
        LocalDate endDate = null;
        final LocalDate now = LocalDate.now();
        final LocalDate defaultStartDate = now.minusMonths(3);
        LocalDate[] expected = {defaultStartDate, now};
        LocalDate[] result = DateUtil.getStartAndEndDates(startDate, endDate);
        assertArrayEquals(expected, result);

        // valid input
        startDate = LocalDate.of(2023, 1, 1);
        endDate = LocalDate.of(2023, 3, 31);
        expected = new LocalDate[]{startDate, endDate};
        result = DateUtil.getStartAndEndDates(startDate, endDate);
        assertArrayEquals(expected, result);

        // endDate is null, should default to current date
        startDate = LocalDate.of(2023, 1, 1);
        endDate = null;
        result = DateUtil.getStartAndEndDates(startDate, endDate);
        endDate = LocalDate.now();
        expected = new LocalDate[]{startDate, endDate};
        assertArrayEquals(expected, result);

        // endDate is after the current date, should throw exception
        startDate = LocalDate.of(2023, 1, 1);
        endDate = LocalDate.now().plusDays(1);
        final LocalDate finalStartDate = startDate;
        final LocalDate finalEndDate = endDate;
        assertThrows(IllegalArgumentException.class, () -> DateUtil.getStartAndEndDates(finalStartDate, finalEndDate));

        // startDate is after endDate, should throw exception
        startDate = LocalDate.of(2023, 1, 1);
        endDate = LocalDate.of(2022, 12, 31);
        final LocalDate finalStartDate1 = startDate;
        final LocalDate finalEndDate1 = endDate;
        assertThrows(IllegalArgumentException.class, () -> DateUtil.getStartAndEndDates(finalStartDate1, finalEndDate1));
    }

    @Test
    void testGetMonthAndYear() {
        assertEquals("June 2023", DateUtil.getMonthAndYear(LocalDate.of(2023, 6, 4)));
    }

}
