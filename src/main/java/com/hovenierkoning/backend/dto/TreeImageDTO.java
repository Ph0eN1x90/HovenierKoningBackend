package com.hovenierkoning.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeImageDTO {
    private long id;
    @NotBlank
    private String imageurl;
    private String description;
}
