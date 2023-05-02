package com.secui.healthone.global.util;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class StringDateTrans {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    @Builder
    public StringDateTrans(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime startDateTime = LocalDate.parse(date, formatter).atStartOfDay();
        LocalDateTime endDateTime = startDateTime.plusDays(1).minusSeconds(1);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
}
