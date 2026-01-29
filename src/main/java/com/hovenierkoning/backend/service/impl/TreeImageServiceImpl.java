package com.hovenierkoning.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hovenierkoning.backend.model.TreeImage;
import com.hovenierkoning.backend.repository.TreeImageRepo;
import com.hovenierkoning.backend.service.TreeImageService;

@Service
public class TreeImageServiceImpl implements TreeImageService {

    @Autowired
    private TreeImageRepo treeImageRepo;

    @Override
    public List<TreeImage> getAllTreeImages() {
        return treeImageRepo.findAll();
    }

    @Override
    public TreeImage getTreeImageById(Long id) {
        return treeImageRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("TreeImage not found with id: " + id));
    }

    @Override
    public List<TreeImage> getTreeImagesByTreeId(Long treeId) {
        return treeImageRepo.findAllByTreeId(treeId);
    }

    @Override
    public TreeImage saveTreeImage(TreeImage treeImage) {
        return treeImageRepo.save(treeImage);
    }

    @Override
    public void saveTreeImages(List<TreeImage> treeImages) {
        treeImageRepo.saveAll(treeImages);
    }

    @Override
    public TreeImage updateTreeImage(TreeImage treeImage, Long id) {
        return treeImageRepo.findById(id)
                .map(existingTreeImage -> {
                    existingTreeImage.setId(treeImage.getId());
                    existingTreeImage.setImageurl(treeImage.getImageurl());
                    return treeImageRepo.save(existingTreeImage);
                })
                .orElseThrow(() -> new RuntimeException("TreeImage not found with id: " + id));
    }

    @Override
    public void deleteTreeImage(Long id) {
        treeImageRepo.deleteById(id);
    }

    @Override
    public void deleteTreeImagesByTreeId(Long treeId) {
        treeImageRepo.deleteByTreeId(treeId);
    }
    
}
