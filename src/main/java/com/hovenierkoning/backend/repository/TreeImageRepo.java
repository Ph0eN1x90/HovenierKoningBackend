package com.hovenierkoning.backend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hovenierkoning.backend.model.TreeImage;

public interface TreeImageRepo extends JpaRepository<TreeImage, Long> {

    List<TreeImage> findAllByTreeId(Long treeId);
    void deleteByTreeId(Long treeId);
    
}
