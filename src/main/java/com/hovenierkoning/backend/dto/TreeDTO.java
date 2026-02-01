package com.hovenierkoning.backend.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeDTO {
    private Long id;
    private Long treenumber;
    private String treetype;
    private double diameter;
    private double height;
    private LocalDate date_finished;
    private Boolean finished;
    private LocalDateTime created;
    private String comment;
    private AddressSummaryDTO address;
    private List<TreeImageDTO> treeimage;
}
