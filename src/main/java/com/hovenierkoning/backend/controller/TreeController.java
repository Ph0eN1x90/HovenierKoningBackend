package com.hovenierkoning.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hovenierkoning.backend.dto.TreeDTO;
import com.hovenierkoning.backend.mapper.TreeMapper;
import com.hovenierkoning.backend.model.Tree;
import com.hovenierkoning.backend.service.TreeService;

@RestController
@RequestMapping("/api/trees")
public class TreeController {

    @Autowired
    private TreeService treeService;
    
    @Autowired
    private TreeMapper treeMapper;


    @PostMapping("/save")
    public ResponseEntity<TreeDTO> saveTree(@RequestBody @Valid TreeDTO treeDTO) {
        Tree tree = treeMapper.toEntity(treeDTO);
        Tree savedTree = treeService.saveTree(tree);
        return new ResponseEntity<>(treeMapper.toDTO(savedTree), HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public List<TreeDTO> getAllTrees() {
        return treeService.getTrees().stream()
                .map(treeMapper::toDTO)
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public ResponseEntity<TreeDTO> getBoomById(@PathVariable("id") long id) {
        Tree tree = treeService.getTreeById(id);
        return new ResponseEntity<>(treeMapper.toDTO(tree), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<TreeDTO> updateTree(@PathVariable("id") long id, @RequestBody @Valid TreeDTO treeDTO) {
        Tree tree = treeMapper.toEntity(treeDTO);
        Tree updatedTree = treeService.updateTree(tree, id);
        return new ResponseEntity<>(treeMapper.toDTO(updatedTree), HttpStatus.OK);
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTree(@PathVariable("id") long id) {
        treeService.deleteTree(id);
        return new ResponseEntity<>("Tree deleted successfully", HttpStatus.OK);
    }


    @GetMapping("/type/{treetype}")
    public List<TreeDTO> getTreesByType(@PathVariable("treetype") String treetype) {
        return treeService.getTreesByType(treetype).stream()
                .map(treeMapper::toDTO)
                .collect(Collectors.toList());
    }


    @GetMapping("/all/{addressId}")
    public List<TreeDTO> getTreesByAddressID(@PathVariable("addressId") String addressId) {
        System.out.println("getTreeByAdresId() called with adresId: " + addressId);
        long addressID = Long.parseLong(addressId);
        return treeService.getTreesByAddressId(addressID).stream()
                .map(treeMapper::toDTO)
                .collect(Collectors.toList());
    }

    // @PostMapping("/saveWithImages")
    // public ResponseEntity<Tree> saveTreeWithImages(@RequestBody Tree tree) {  
    //     System.out.println("saveTreeWithImages() called with: " + tree);

    //     return new ResponseEntity<>(treeService.saveTreeWithImages(tree), HttpStatus.CREATED);
    // }

}