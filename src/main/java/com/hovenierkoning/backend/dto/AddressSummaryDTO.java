package com.hovenierkoning.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressSummaryDTO {
    private Long id;
    private String streetname;
    private String city;
    private String zipcode;
    private int housenumber;
}
