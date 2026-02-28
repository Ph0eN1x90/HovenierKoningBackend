package com.hovenierkoning.backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import com.hovenierkoning.backend.dto.TreeDTO;
import com.hovenierkoning.backend.dto.TreeSummaryDTO;
import com.hovenierkoning.backend.model.Tree;

@Mapper(componentModel = "spring", uses = {TreeImageMapper.class})
public interface TreeMapper {

    TreeDTO toDTO(Tree tree);
    
    List<TreeDTO> toDTOs(List<Tree> trees);
    
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "address.finished", ignore = true)
    @Mapping(target = "address.date_finished", ignore = true)
    @Mapping(target = "address.trees", ignore = true)
    Tree toEntity(TreeDTO treeDTO);
    
    List<Tree> toEntities(List<TreeDTO> treeDTOs);
    
    TreeSummaryDTO toSummaryDTO(Tree tree);
    
    List<TreeSummaryDTO> toSummaryDTOs(List<Tree> trees);
}
