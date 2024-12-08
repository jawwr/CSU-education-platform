package com.csu.edu.repository;

import com.csu.edu.model.SuperGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SuperGameRepository extends JpaRepository<SuperGame, Long> {
    List<SuperGame> findAllByCategoryId(Long categoryId);
}
