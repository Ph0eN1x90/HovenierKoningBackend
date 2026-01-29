package com.hovenierkoning.backend.service;

import java.util.List;
import com.hovenierkoning.backend.model.Tree;

public interface TreeService {

    Tree saveTree(Tree tree);
    List<Tree> getTrees();
    Tree getTreeById(long id);
    List<Tree> getTreesByType(String treeType);
    Tree updateTree(Tree tree,long id);
    void deleteTree(long id);
    List<Tree> getTreesByAddressId(long addressId);
}
