package com.hovenierkoning.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hovenierkoning.backend.dto.TreeImageDTO;
import com.hovenierkoning.backend.mapper.TreeImageMapper;
import com.hovenierkoning.backend.model.TreeImage;
import com.hovenierkoning.backend.service.TreeImageService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "TreeImage", description = "Endpoints for managing tree images")
@RequestMapping("/api/tree-images")
public class TreeImageController {
    
    @Autowired
    private TreeImageService treeImageService;

    @Autowired
    private TreeImageMapper treeImageMapper;

    @GetMapping("/all")
    public List<TreeImageDTO> getAllTreeImages() {
        return treeImageService.getAllTreeImages().stream()
                .map(treeImageMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/all/{treeId}")
    public List<TreeImageDTO> getTreeImagesByTreeId(@PathVariable("treeId") long treeId) {
        return treeImageService.getTreeImagesByTreeId(treeId).stream()
                .map(treeImageMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/save")
    public ResponseEntity<TreeImageDTO> saveTreeImage(@RequestBody @Valid TreeImageDTO treeImageDTO) {
        TreeImage treeImage = treeImageMapper.toEntity(treeImageDTO);
        TreeImage savedTreeImage = treeImageService.saveTreeImage(treeImage);
        return new ResponseEntity<>(treeImageMapper.toDTO(savedTreeImage), HttpStatus.CREATED);
    }

    @PostMapping("/save-all")
    public ResponseEntity<List<TreeImageDTO>> saveTreeImages(@RequestBody List<@Valid TreeImageDTO> treeImageDTOs) {
        List<TreeImage> treeImages = treeImageMapper.toEntities(treeImageDTOs);
        List<TreeImage> savedTreeImages = treeImageService.saveTreeImages(treeImages);
        List<TreeImageDTO> savedTreeImageDTOs = savedTreeImages.stream()
                .map(treeImageMapper::toDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(savedTreeImageDTOs, HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<TreeImageDTO> updateTreeImage(@RequestBody @Valid TreeImageDTO treeImageDTO, @PathVariable("id") long id) {
        TreeImage treeImage = treeImageMapper.toEntity(treeImageDTO);
        TreeImage updatedTreeImage = treeImageService.updateTreeImage(treeImage, id);
        return new ResponseEntity<>(treeImageMapper.toDTO(updatedTreeImage), HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTreeImage(@PathVariable("id") long id) {
        treeImageService.deleteTreeImage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/delete-by-tree-id/{treeId}")
    public ResponseEntity<Void> deleteTreeImagesByTreeId(@PathVariable("treeId") long treeId) {
        treeImageService.deleteTreeImagesByTreeId(treeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
