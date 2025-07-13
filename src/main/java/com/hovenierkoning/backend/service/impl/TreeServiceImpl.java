package com.hovenierkoning.backend.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hovenierkoning.backend.model.Tree;
import com.hovenierkoning.backend.repository.TreeRepo;
import com.hovenierkoning.backend.service.TreeService;

@Service
public class TreeServiceImpl implements TreeService {

    @Autowired
    private TreeRepo treeRepository;

    @Override
    public Tree saveTree(Tree boom) {

        return treeRepository.save(boom);
    }

    @Override
    public List<Tree> getTrees() {
        return treeRepository.findAll();
    }

    @Override
    public Tree getTreeById(long id) {
        return treeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tree not found with id: " + id));
    }

    @Override
    public Tree updateTree(Tree tree, long id) {
        return treeRepository.findById(id)
                .map(existingTree -> {
                    existingTree.setTreetype(tree.getTreetype());
                    existingTree.setHeight(tree.getHeight());
                    existingTree.setDiameter(tree.getDiameter());
                    return treeRepository.save(existingTree);
                })
                .orElseThrow(() -> new RuntimeException("Tree not found with id: " + id));
    }

    @Override
    public void deleteTree(long id) {
        treeRepository.deleteById(id);
    }

    @Override
    public List<Tree> getTreesByType(String boomType) {
        return treeRepository.getTreeByTreetype(boomType);
    }

    @Override
    public List<Tree> getTreesByAddressId(long addressId) {
        return treeRepository.getTreeByAddressId(addressId);
    }

}  