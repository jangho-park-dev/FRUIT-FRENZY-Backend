package com.example.match3.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    // 지금은 평문 저장(나중에 암호화 붙이면 됨)
    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "best_score", nullable = false)
    private int bestScore = 0;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    protected User() { }

    public User(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }
    public int getBestScore() { return bestScore; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public boolean checkPassword(String rawPassword) {
        return this.passwordHash.equals(rawPassword);
    }

    public boolean updateBestScoreIfHigher(int newScore) {
        if (newScore > this.bestScore) {
            this.bestScore = newScore;
            return true;
        }
        return false;
    }

}
