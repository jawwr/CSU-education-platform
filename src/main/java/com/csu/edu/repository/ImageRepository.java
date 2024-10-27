package com.csu.edu.repository;

import com.csu.edu.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    Optional<Image> findImageByLink(String link);
}
