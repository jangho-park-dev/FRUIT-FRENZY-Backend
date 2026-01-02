package com.example.match3.dto;

import jakarta.validation.constraints.Min;

public class ScoreDtos {

    public record SubmitScoreRequest(
            @Min(0) int score
    ) {}

    public record SubmitScoreResponse(
            String username,
            int before,
            int after,
            boolean updated
    ) {}

    public record MyScoreResponse(
            String username,
            int bestScore
    ) {}

    public record RankResponse(
            String username,
            int bestScore
    ) {}
}
