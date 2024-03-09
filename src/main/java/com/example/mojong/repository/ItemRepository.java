package com.example.mojong.repository;

import com.example.mojong.model.entity.Category;
import com.example.mojong.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByCategoryAndEnabledTrue(Category category);

    boolean existsByNameAndEnabledTrue(String name);
}
