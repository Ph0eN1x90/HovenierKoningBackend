package com.hovenierkoning.backend.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeSummaryDTO {
    private Long id;
    private Long treenumber;
    private String treetype;
    private double diameter;
    private double height;
    private Boolean finished;
    private LocalDate date_finished;
}
