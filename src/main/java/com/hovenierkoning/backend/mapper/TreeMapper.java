package com.hovenierkoning.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.hovenierkoning.backend.dto.TreeDTO;
import com.hovenierkoning.backend.dto.TreeSummaryDTO;
import com.hovenierkoning.backend.model.Tree;

@Mapper(componentModel = "spring", uses = {TreeImageMapper.class})
public interface TreeMapper {
    
    TreeMapper INSTANCE = Mappers.getMapper(TreeMapper.class);
    
    TreeDTO toDTO(Tree tree);
    
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "address.finished", ignore = true)
    @Mapping(target = "address.date_finished", ignore = true)
    @Mapping(target = "address.trees", ignore = true)
    Tree toEntity(TreeDTO treeDTO);
    
    TreeSummaryDTO toSummaryDTO(Tree tree);
}
