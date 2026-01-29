package com.hovenierkoning.backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hovenierkoning.backend.model.Tree;

public interface TreeRepo extends JpaRepository<Tree, Long>{

    List<Tree> getTreeByTreetype(String treeType);
    List<Tree> getTreeByAddressId(long addressId);
}
