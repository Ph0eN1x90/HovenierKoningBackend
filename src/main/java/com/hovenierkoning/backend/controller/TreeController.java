package com.hovenierkoning.backend.controller;

import java.util.List;

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
import com.hovenierkoning.backend.model.Tree;
import com.hovenierkoning.backend.service.TreeService;

@RestController
@RequestMapping("/api/trees")
public class TreeController {

    @Autowired
    private TreeService treeService;


    @PostMapping("/save")
    public ResponseEntity<Tree> saveTree(@RequestBody Tree tree) {  

        return new ResponseEntity<>(treeService.saveTree(tree), HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public List<Tree> getAllTrees() {
        return treeService.getTrees();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Tree> getBoomById(@PathVariable("id") long id) {
        return new ResponseEntity<>(treeService.getTreeById(id), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Tree> updateTree(@PathVariable("id") long id, @RequestBody Tree tree) {
        return new ResponseEntity<>(treeService.updateTree(tree, id), HttpStatus.OK);  
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTree(@PathVariable("id") long id) {
        treeService.deleteTree(id);
        return new ResponseEntity<>("Tree deleted successfully", HttpStatus.OK);
    }


    @GetMapping("/type/{treetype}")
    public List<Tree> getTreesByType(@PathVariable("treetype") String treetype) {
        return treeService.getTreesByType(treetype);
    }


    @GetMapping("/all/{addressId}")
    public List<Tree> getTreesByAddressID(@PathVariable("addressId") String addressId) {
        System.out.println("getTreeByAdresId() called with adresId: " + addressId);
        long addressID = Long.parseLong(addressId);
        return treeService.getTreesByAddressId(addressID);
    }

    // @PostMapping("/saveWithImages")
    // public ResponseEntity<Tree> saveTreeWithImages(@RequestBody Tree tree) {  
    //     System.out.println("saveTreeWithImages() called with: " + tree);

    //     return new ResponseEntity<>(treeService.saveTreeWithImages(tree), HttpStatus.CREATED);
    // }

}