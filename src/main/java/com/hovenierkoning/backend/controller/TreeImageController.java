package com.hovenierkoning.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hovenierkoning.backend.model.TreeImage;
import com.hovenierkoning.backend.repository.TreeImageRepo;

@RestController
@RequestMapping("/api/tree-images")
public class TreeImageController {
    
    @Autowired
    private TreeImageRepo treeImageRepo;

    // For example:
    @GetMapping("/all")
    public List<TreeImage> getAllTreeImages() {
        // Logic to retrieve all tree images 
        return treeImageRepo.findAll();
    }

    @GetMapping("/all/{treeId}")
    public List<TreeImage> getTreeImagesByTreeId(@PathVariable("treeId") String treeId) {
        long treeID = Long.parseLong(treeId);
        return treeImageRepo.findAllByTreeId(treeID);
    }

    @PostMapping("/save")
    public ResponseEntity<TreeImage> saveTreeImage(@RequestBody TreeImage treeImage) {
        return new ResponseEntity<>(treeImageRepo.save(treeImage), HttpStatus.CREATED);
    }

    @PostMapping("/save-all")
    public ResponseEntity<List<TreeImage>> saveTreeImages(@RequestBody List<TreeImage> treeImages) {
        return new ResponseEntity<>(treeImageRepo.saveAll(treeImages), HttpStatus.CREATED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<TreeImage> updateTreeImage(@RequestBody TreeImage treeImage, Long id) {
        return treeImageRepo.findById(id)
                .map(existingTreeImage -> {
                    existingTreeImage.setImageurl(treeImage.getImageurl());
                    return new ResponseEntity<>(treeImageRepo.save(existingTreeImage), HttpStatus.OK);
                })
                .orElseThrow(() -> new RuntimeException("TreeImage not found with id: " + id));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTreeImage(Long id) {
        treeImageRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/delete-by-tree-id/{treeId}")
    public ResponseEntity<Void> deleteTreeImagesByTreeId(Long treeId) {
        treeImageRepo.deleteByTreeId(treeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
