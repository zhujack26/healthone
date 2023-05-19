package com.secui.healthone.global.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Slf4j
@AllArgsConstructor
@Builder
public class StringDateTrans {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public StringDateTrans(String date) {
        // 2023-05-04T10:05:58.6375293
        if(date.length() == 10)
            date = date + "T00:00:00.000";
        if(date.length() == 19)
            date = date + ".000";
        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        LocalDateTime startDateTime = dateTime.with(LocalTime.MIN);
        LocalDateTime endDateTime = dateTime.with(LocalTime.MAX);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public static StringDateTrans stringDateTrans30Days(String date) {
        // 2023-05-04T10:05:58.6375293
        if(date.length() == 10)
            date = date + "T00:00:00.000";
        if(date.length() == 19)
            date = date + ".000";
        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        LocalDateTime startDateTime = dateTime.with(LocalTime.MIN);
        startDateTime = startDateTime.minusMonths(1);
        LocalDateTime endDateTime = dateTime.with(LocalTime.MAX);
        return StringDateTrans.builder()
                .startDateTime(startDateTime)
                .endDateTime(endDateTime)
                .build();
    }

    @Builder
    public StringDateTrans(LocalDateTime date) {
        log.info("date: {}", date);
        LocalDateTime startDateTime = date.with(LocalTime.MIN);
        LocalDateTime endDateTime = date.with(LocalTime.MAX);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
}

