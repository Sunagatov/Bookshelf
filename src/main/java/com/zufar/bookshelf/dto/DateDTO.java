package com.zufar.bookshelf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateDTO {

    private Integer year;
    private Integer month;
    private Integer day;
}