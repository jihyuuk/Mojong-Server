package com.example.mojong.repository;

import com.example.mojong.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category>  findAllByEnabledTrue();

    Boolean existsByNameAndEnabledTrue(String name);

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.items i WHERE c.enabled = true And  (i.enabled = true OR i IS NULL) ORDER BY c.seq DESC, c.id ASC, i.seq DESC, i.id ASC")
    List<Category> findAllCustom();

}
