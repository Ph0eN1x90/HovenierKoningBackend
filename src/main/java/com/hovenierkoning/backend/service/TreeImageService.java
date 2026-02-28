package com.hovenierkoning.backend.service;

import java.util.List;
import com.hovenierkoning.backend.model.TreeImage;

public interface TreeImageService {
    List<TreeImage> getAllTreeImages();
    TreeImage getTreeImageById( long id);
    List<TreeImage> getTreeImagesByTreeId( long treeId);
    TreeImage saveTreeImage( TreeImage treeImage);
    List<TreeImage> saveTreeImages( List<TreeImage> treeImages);
    TreeImage updateTreeImage( TreeImage treeImage, long id);
    void deleteTreeImage( long id);
    void deleteTreeImagesByTreeId( long treeId);   
}
