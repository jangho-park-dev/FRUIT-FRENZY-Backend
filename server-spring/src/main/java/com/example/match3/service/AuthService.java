package com.example.match3.service;

import com.example.match3.entity.User;
import com.example.match3.repository.UserRepository;
import com.example.match3.security.JwtUtil;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthService(
            UserRepository userRepository,
            BCryptPasswordEncoder encoder,
            JwtUtil jwtUtil
    ) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    public User signup(String username, String rawPassword) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("이미 존재하는 username");
        }
        return userRepository.save(
                new User(username, encoder.encode(rawPassword))
        );
    }

    public String loginAndIssueToken(String username, String rawPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));

        if (!encoder.matches(rawPassword, user.getPasswordHash())) {
            throw new IllegalArgumentException("비밀번호 틀림");
        }

        return jwtUtil.generateToken(user.getId());
    }
}

