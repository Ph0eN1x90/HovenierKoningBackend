package com.hovenierkoning.backend.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private Long id;
    private String streetname;
    private String city;
    private String zipcode;
    private int housenumber;
    private Boolean finished;
    private LocalDate date_finished;
    private List<TreeSummaryDTO> trees;
}
