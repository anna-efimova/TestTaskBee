package com.example.testtaskwebbee.taskInT;
import com.example.testtaskwebbee.task.WorkDayChecker;
import org.junit.jupiter.api.Test;
import java.time.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WorkDayCheckerTestIT {

    @Test
    public void integrationTest() {

        // Праздничный день, должно быть нерабочее время
        LocalDate date = LocalDate.of(2024, 5, 1);
        ZonedDateTime dateTime = ZonedDateTime.of(date, LocalTime.of(10, 0), ZoneId.of("UTC"));
        assertTrue(WorkDayChecker.isWeekend(date));
        assertFalse(WorkDayChecker.isWorkingHours(dateTime));

        // Рабочий день, рабочее время
        date = LocalDate.of(2024, 5, 2);
        dateTime = ZonedDateTime.of(date, LocalTime.of(10, 0), ZoneId.of("UTC"));
        assertFalse(WorkDayChecker.isWeekend(date));
        assertTrue(WorkDayChecker.isWorkingHours(dateTime));

        // Рабочий день, нерабочее время
        date = LocalDate.of(2024, 5, 2);
        dateTime = ZonedDateTime.of(date, LocalTime.of(19, 0), ZoneId.of("UTC"));
        assertFalse(WorkDayChecker.isWeekend(date));
        assertFalse(WorkDayChecker.isWorkingHours(dateTime));

        // Выходной день, нерабочее время
        date = LocalDate.of(2024, 5, 4);
        dateTime = ZonedDateTime.of(date, LocalTime.of(10, 0), ZoneId.of("UTC"));
        assertTrue(WorkDayChecker.isWeekend(date));
        assertFalse(WorkDayChecker.isWorkingHours(dateTime));
    }
}
