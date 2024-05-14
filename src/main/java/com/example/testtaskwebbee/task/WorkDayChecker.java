package com.example.testtaskwebbee.task;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class WorkDayChecker {

    private static final Set<LocalDate> WEEKENDS = new HashSet<>();

    static {
        for (int day : new int[]{1, 4, 5, 9, 11, 12, 18, 19, 25, 26}) {
            WEEKENDS.add(LocalDate.of(2024, 5, day));
        }
    }

    /**
     * Проверяет, является ли переданная дата выходным днем в мае 2024 года.
     *
     * @param date Дата для проверки (тип {@link LocalDate}).
     * @return true если дата является выходным днем, иначе false.
     */
    public static boolean isWeekend(LocalDate date) {
        return WEEKENDS.contains(date);
    }

    /**
     * Проверяет, является ли переданная дата и время рабочими в соответствии с установленным графиком работы и производственным календарем.
     *
     * @param dateTime Дата и время для проверки (тип {@link ZonedDateTime}).
     * @return false если дата и время НЕ являются рабочими, иначе true.
     */
    public static boolean isWorkingHours(ZonedDateTime dateTime) {
        ZonedDateTime moscowTime = dateTime.withZoneSameInstant(ZoneId.of("Europe/Moscow"));
        LocalDate date = moscowTime.toLocalDate();
        LocalTime time = moscowTime.toLocalTime();

        boolean isWeekend = isWeekend(date);
        boolean isWorkingDay = !moscowTime.getDayOfWeek().equals(DayOfWeek.SATURDAY) &&
                !moscowTime.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean isWorkingTime = !time.isBefore(LocalTime.of(9, 0)) && time.isBefore(LocalTime.of(18, 0));

        return !isWeekend && isWorkingDay && isWorkingTime;
    }

    public static void main(String[] args) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss zzzz");

        // Пример проверки выходного дня
        LocalDate date = LocalDate.of(2024, 5, 1);
        boolean isWeekend = isWeekend(date);
        System.out.println(date.format(dateFormatter) + " является выходным днем? " + (isWeekend ? "Да" : "Нет"));

        // Пример проверки рабочего времени
        ZonedDateTime dateTime = ZonedDateTime.of(2024, 5, 1, 10, 0, 0, 0, ZoneId.of("UTC"));
        boolean isWorkingTime = isWorkingHours(dateTime);
        System.out.println("Дата и время " + dateTime.format(timeFormatter) + " являются рабочими? " + (isWorkingTime ? "Да" : "Нет"));
    }
}

