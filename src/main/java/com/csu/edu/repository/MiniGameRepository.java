package com.csu.edu.repository;

import com.csu.edu.model.MiniGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MiniGameRepository extends JpaRepository<MiniGame, Long> {

    @Query("""
            SELECT mg
            FROM MiniGame mg
            JOIN FETCH mg.image i
            JOIN FETCH mg.choices ch
            WHERE mg.id = :id
            """)
    Optional<MiniGame> findMiniGameWithImageAndChoicesById(@Param("id") Long id);
}
