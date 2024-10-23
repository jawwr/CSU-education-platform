package com.csu.edu.repository;

import com.csu.edu.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = """
            SELECT c
            FROM Category c
            JOIN FETCH c.image i
            WHERE c.id = :id
            """)
    Optional<Category> findCategoryWithImageById(@Param("id") int id);

    @Query(value = """
            SELECT c
            FROM Category c
            JOIN FETCH c.image i
            """)
    List<Category> findAllWithImage();
}
