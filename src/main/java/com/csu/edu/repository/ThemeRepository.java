package com.csu.edu.repository;

import com.csu.edu.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
    @Query("""
            SELECT t
            FROM Theme t
            JOIN FETCH t.image
            WHERE t.categoryId = :categoryId
            """)
    List<Theme> findAllByCategoryIdWithImages(@Param("categoryId") Long categoryId);

    @Query("""
            SELECT t
            FROM Theme t
            JOIN FETCH t.image
            WHERE t.id = :id
            """)
    Optional<Theme> findByIdWithImage(@Param("id") Long id);
}
