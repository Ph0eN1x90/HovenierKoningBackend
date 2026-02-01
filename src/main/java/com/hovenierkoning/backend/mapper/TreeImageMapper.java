package com.hovenierkoning.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.hovenierkoning.backend.dto.TreeImageDTO;
import com.hovenierkoning.backend.model.TreeImage;

@Mapper(componentModel = "spring")
public interface TreeImageMapper {
    
    TreeImageMapper INSTANCE = Mappers.getMapper(TreeImageMapper.class);
    
    @Mapping(source = "imageurl", target = "imageurl")
    @Mapping(target = "description", ignore = true)
    TreeImageDTO toDTO(TreeImage treeImage);
    
    @Mapping(source = "imageurl", target = "imageurl")
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "tree", ignore = true)
    TreeImage toEntity(TreeImageDTO treeImageDTO);
}
