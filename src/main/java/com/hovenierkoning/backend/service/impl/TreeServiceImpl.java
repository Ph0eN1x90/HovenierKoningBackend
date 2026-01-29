package com.hovenierkoning.backend.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hovenierkoning.backend.model.Tree;
import com.hovenierkoning.backend.model.TreeImage;
import com.hovenierkoning.backend.repository.TreeImageRepo;
import com.hovenierkoning.backend.repository.TreeRepo;
import com.hovenierkoning.backend.service.TreeService;

@Service
public class TreeServiceImpl implements TreeService {

    @Autowired
    private TreeRepo treeRepo;
    @Autowired
    private TreeImageRepo treeImageRepo;

    /**
     * Saves a tree entity to the database along with its associated tree images.
     * 
     * This method handles the persistence of a tree and its related images in a two-step process:
     * 1. First, it temporarily removes the tree images from the tree entity to save the tree independently
     * 2. Then, it saves the tree images separately, associating them with the saved tree
     * 3. Finally, it reattaches the saved images to the tree entity before returning
     */
    @Override
    public Tree saveTree(Tree tree) {

        List<TreeImage> treeImages = tree.getTreeimage();
        tree.setTreeimage(null);
        Tree savedTree = treeRepo.save(tree);
        
        if (treeImages != null && !treeImages.isEmpty()) {
            treeImages.forEach(image -> image.setTree(savedTree));
            treeImageRepo.saveAll(treeImages);
            savedTree.setTreeimage(treeImages);
        }
        
        return savedTree;
    }

    @Override
    public List<Tree> getTrees() {
        return treeRepo.findAll();
    }

    @Override
    public Tree getTreeById(long id) {
        return treeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Tree not found with id: " + id));
    }

    @Override
    public Tree updateTree(Tree tree, long id) {
        Tree existingTree = treeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Tree not found with id: " + id));
        
        // Update basic tree properties
        existingTree.setTreenumber(tree.getTreenumber());
        existingTree.setTreetype(tree.getTreetype());
        existingTree.setHeight(tree.getHeight());
        existingTree.setDiameter(tree.getDiameter());
        existingTree.setComment(tree.getComment());
        existingTree.setDate_finished(tree.getDate_finished());
        existingTree.setFinished(tree.getFinished());
        existingTree.setAddress(tree.getAddress());
        
        // Handle tree images - delete old ones
        if (existingTree.getTreeimage() != null && !existingTree.getTreeimage().isEmpty()) {
            treeImageRepo.deleteAll(existingTree.getTreeimage());
            existingTree.getTreeimage().clear();
        }
        
        // Save updated tree (JPA will UPDATE because entity already has an ID)
        Tree updatedTree = treeRepo.save(existingTree);
        
        // Add new tree images
        if (tree.getTreeimage() != null && !tree.getTreeimage().isEmpty()) {
            tree.getTreeimage().forEach(image -> {
                image.setTree(updatedTree);
                image.setId(null); // Ensure new images get new IDs
            });
            List<TreeImage> savedImages = treeImageRepo.saveAll(tree.getTreeimage());
            updatedTree.setTreeimage(savedImages);
        }
        
        return updatedTree;
    }

    @Override
    public void deleteTree(long id) {
        treeRepo.deleteById(id);
    }

    @Override
    public List<Tree> getTreesByType(String boomType) {
        return treeRepo.getTreeByTreetype(boomType);
    }

    @Override
    public List<Tree> getTreesByAddressId(long addressId) {
        return treeRepo.getTreeByAddressId(addressId);
    }

    // @Override
    // public Tree saveTreeWithImages(Tree tree) {

    //     // List<TreeImage> treeImages = tree.getTreeimage();
    //     // tree.setTreeimage(null);
    //     // Tree savedTree = treeRepo.save(tree);
    //     // savedTree.setTreeimage(treeImageRepo.saveAll(treeImages));                                      // TODO: de tree ids missen in treeimages.
    //     // System.out.print("test" + savedTree);
    //     // return savedTree;

    //     return treeRepo.save(tree);
    // }

}  