package com.hovenierkoning.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeImageDTO {
    private Long id;
    private String imageurl;
    private String description;
}
