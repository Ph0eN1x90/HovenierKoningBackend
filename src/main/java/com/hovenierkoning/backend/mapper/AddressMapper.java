package com.hovenierkoning.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.hovenierkoning.backend.dto.AddressDTO;
import com.hovenierkoning.backend.dto.AddressSummaryDTO;
import com.hovenierkoning.backend.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
    
    AddressDTO toDTO(Address address);
    
    @Mapping(target = "trees", ignore = true)
    Address toEntity(AddressDTO addressDTO);
    
    AddressSummaryDTO toSummaryDTO(Address address);
}
