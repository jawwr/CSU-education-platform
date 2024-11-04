package com.csu.edu.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "theories")
public class Theory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "NUMERIC")
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "page_number", nullable = false, columnDefinition = "NUMERIC")
    private Integer pageNumber;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", columnDefinition = "NUMERIC")
    private Image image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id", columnDefinition = "NUMERIC")
    private Theme theme;

    @Column(name = "theme_id", insertable = false, updatable = false, columnDefinition = "NUMERIC")
    private Long themeId;
}
