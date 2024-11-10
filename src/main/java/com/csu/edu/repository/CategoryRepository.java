package com.csu.edu.repository;

import com.csu.edu.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = """
            SELECT c
            FROM Category c
            JOIN FETCH c.image i
            LEFT JOIN FETCH c.miniGames
            WHERE c.id = :id
            """)
    Optional<Category> findCategoryWithImageAndMiniGameById(@Param("id") int id);

    @Query(value = """
            SELECT c
            FROM Category c
            JOIN FETCH c.image i
            """)
    List<Category> findAllWithImage();

    @Modifying
    @Query(value = """
            DELETE FROM categories
            WHERE id = :id
            """, nativeQuery = true)
    void deleteCategoryById(@Param("id") int id);
}
