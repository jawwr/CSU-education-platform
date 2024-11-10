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
@Table(name = "mini_games_choices")
public class MiniGameChoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "NUMERIC")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "isCorrect", nullable = false)
    private boolean isCorrect;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", columnDefinition = "NUMERIC")
    private Image image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mini_game_id")
    private MiniGame miniGame;
}
