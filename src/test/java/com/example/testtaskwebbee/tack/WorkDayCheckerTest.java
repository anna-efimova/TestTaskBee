package com.example.testtaskwebbee.tack;
import com.example.testtaskwebbee.task.WorkDayChecker;
import org.junit.jupiter.api.Test;
import java.time.*;

import static org.junit.jupiter.api.Assertions.*;
public class WorkDayCheckerTest {
    @Test
    public void testIsWeekend() {
        assertTrue(WorkDayChecker.isWeekend(LocalDate.of(2024, 5, 1)));
        assertFalse(WorkDayChecker.isWeekend(LocalDate.of(2024, 5, 2)));
    }

    @Test
    public void testIsWorkingHours() {
        ZonedDateTime dateTime = ZonedDateTime.of(2024, 5, 1, 10, 0, 0, 0, ZoneId.of("UTC"));
        assertFalse(WorkDayChecker.isWorkingHours(dateTime));

        dateTime = ZonedDateTime.of(2024, 5, 2, 10, 0, 0, 0, ZoneId.of("UTC"));
        assertTrue(WorkDayChecker.isWorkingHours(dateTime));
    }
}
