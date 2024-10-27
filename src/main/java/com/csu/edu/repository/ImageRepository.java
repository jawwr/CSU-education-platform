package com.csu.edu.repository;

import com.csu.edu.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    Optional<Image> findImageByLink(String link);

    @Modifying
    @Query(value = """
            DELETE FROM images
            WHERE link = :link
            """, nativeQuery = true)
    void deleteByLink(@Param("link") String link);
}
