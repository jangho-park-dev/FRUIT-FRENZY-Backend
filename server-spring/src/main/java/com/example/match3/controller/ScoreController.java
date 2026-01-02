package com.example.match3.controller;

import com.example.match3.dto.ScoreDtos;
import com.example.match3.service.ScoreService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/score")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping("/submit")
    public ScoreDtos.SubmitScoreResponse submit(
        @Valid @RequestBody ScoreDtos.SubmitScoreRequest req, Authentication auth) {
        Long userId = (Long) auth.getPrincipal();
        return scoreService.submitScore(userId, req.score());
    }

    @GetMapping("/mine")
    public ScoreDtos.MyScoreResponse mine(Authentication auth) {
        Long userId = (Long) auth.getPrincipal();
        return scoreService.myScore(userId);
    }

    @GetMapping("/top10")
    public List<ScoreDtos.RankResponse> top10() {
        return scoreService.top10();
    }
}
