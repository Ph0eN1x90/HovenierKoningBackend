package com.hovenierkoning.backend.service;

import java.util.List;
import com.hovenierkoning.backend.model.TreeImage;

public interface TreeImageService {
    List<TreeImage> getAllTreeImages();
    TreeImage getTreeImageById(Long id);
    List<TreeImage> getTreeImagesByTreeId(Long treeId);
    TreeImage saveTreeImage(TreeImage treeImage);
    void saveTreeImages(List<TreeImage> treeImages);
    TreeImage updateTreeImage(TreeImage treeImage, Long id);
    void deleteTreeImage(Long id);
    void deleteTreeImagesByTreeId(Long treeId);   
}
