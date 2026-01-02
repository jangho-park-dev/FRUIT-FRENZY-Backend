package com.example.match3.controller;

import com.example.match3.service.AuthService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // ===== 회원가입 =====
    @PostMapping("/signup")
    public Map<String, String> signup(@Valid @RequestBody SignupRequest request) {
        authService.signup(request.username(), request.password());
        return Map.of("username", request.username());
    }

    // ===== 로그인 (JWT 발급) =====
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {
        String token = authService.loginAndIssueToken(
                request.username(),
                request.password()
        );
        return Map.of("token", token);
    }
    
    @PostMapping("/test")
    public Map<String, String> test() { 
        return Map.of("test", "test");
    }

    // ===== Request DTO =====
    public record SignupRequest(
            @NotBlank String username,
            @NotBlank String password
    ) {}

    public record LoginRequest(
            @NotBlank String username,
            @NotBlank String password
    ) {}
}
