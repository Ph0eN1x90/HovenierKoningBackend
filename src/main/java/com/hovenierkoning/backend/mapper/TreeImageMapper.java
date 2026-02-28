package com.hovenierkoning.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import com.hovenierkoning.backend.dto.TreeImageDTO;
import com.hovenierkoning.backend.model.TreeImage;

@Mapper(componentModel = "spring")
public interface TreeImageMapper {

    @Mapping(source = "imageurl", target = "imageurl")
    @Mapping(target = "description", ignore = true)
    TreeImageDTO toDTO(TreeImage treeImage);

    List<TreeImageDTO> toDTOs(List<TreeImage> treeImages);
    
    @Mapping(source = "imageurl", target = "imageurl")
    @Mapping(target = "tree", ignore = true)
    @Mapping(target = "created", ignore = true)
    TreeImage toEntity(TreeImageDTO treeImageDTO);

    List<TreeImage> toEntities(List<TreeImageDTO> treeImageDTOs);
}
