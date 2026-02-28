package com.hovenierkoning.backend.dto;

import java.time.LocalDate;
import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private Long id;
    @NotBlank
    private String streetname;
    @NotBlank
    private String city;
    @NotBlank
    private String zipcode;
    @Positive
    private int housenumber;
    @NotNull
    private Boolean finished;
    private LocalDate date_finished;
    @Valid
    private List<TreeSummaryDTO> trees;
}
