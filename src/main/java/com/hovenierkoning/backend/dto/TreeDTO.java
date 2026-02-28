package com.hovenierkoning.backend.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeDTO {
    private Long id;
    @NotNull
    @Positive
    private Long treenumber;
    @NotBlank
    private String treetype;
    @PositiveOrZero
    private double diameter;
    @PositiveOrZero
    private double height;
    private LocalDate date_finished;
    @NotNull
    private Boolean finished;
    private LocalDateTime created;
    private String comment;
    private AddressSummaryDTO address;
    @Valid
    private List<TreeImageDTO> treeimage;
}
