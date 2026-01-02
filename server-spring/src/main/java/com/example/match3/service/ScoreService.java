package com.example.match3.service;

import com.example.match3.dto.ScoreDtos;
import com.example.match3.entity.User;
import com.example.match3.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreService {

    private final UserRepository userRepository;

    public ScoreService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public ScoreDtos.SubmitScoreResponse submitScore(Long userId, int score) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("유저 없음"));

        int before = user.getBestScore();
        boolean updated = user.updateBestScoreIfHigher(score);

        return new ScoreDtos.SubmitScoreResponse(
            user.getUsername(),
            before,
            user.getBestScore(),
            updated
        );
    }

    public ScoreDtos.MyScoreResponse myScore(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("유저 없음"));

        return new ScoreDtos.MyScoreResponse(
            user.getUsername(),
            user.getBestScore()
        );
    }


    public List<ScoreDtos.RankResponse> top10() {
        return userRepository.findTop10ByOrderByBestScoreDesc()
                .stream()
                .map(u -> new ScoreDtos.RankResponse(u.getUsername(), u.getBestScore()))
                .collect(Collectors.toList());
    }
}
