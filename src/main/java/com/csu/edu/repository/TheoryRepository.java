package com.csu.edu.repository;

import com.csu.edu.model.Theory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TheoryRepository extends JpaRepository<Theory, Long> {

    @Query("""
            SELECT t
            FROM Theory t
            JOIN FETCH t.image
            WHERE t.themeId = :themeId
            ORDER BY t.pageNumber
            """)
    List<Theory> findAllByThemeIdWithImage(@Param("themeId") Long themeId);
}
