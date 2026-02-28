package com.hovenierkoning.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import com.hovenierkoning.backend.dto.AddressDTO;
import com.hovenierkoning.backend.dto.AddressSummaryDTO;
import com.hovenierkoning.backend.model.Address;

@Mapper(componentModel = "spring", uses = {TreeMapper.class})
public interface AddressMapper {

    @Mapping(source = "trees", target = "trees")
    AddressDTO toDTO(Address address);

    List<AddressDTO> toDTOs(List<Address> addresses);
    
    @Mapping(target = "trees", ignore = true)
    Address toEntity(AddressDTO addressDTO);

    List<Address> toEntities(List<AddressDTO> addressDTOs);
    
    AddressSummaryDTO toSummaryDTO(Address address);

    List<AddressSummaryDTO> toSummaryDTOs(List<Address> addresses);
}
