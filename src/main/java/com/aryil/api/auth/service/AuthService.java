package com.aryil.api.auth.service;

import com.aryil.api.auth.dto.LoginRequest;
import com.aryil.api.auth.dto.LoginResponse;
import com.aryil.api.auth.dto.RegisterRequest;
import com.aryil.api.auth.dto.RegisterResponse;
import com.aryil.api.auth.exception.EmailAlreadyExistsException;
import com.aryil.api.auth.security.JwtUtil;
import com.aryil.api.user.entity.User;
import com.aryil.api.user.mapper.UserMapper;
import com.aryil.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;  // <<— Artık burada final olarak tanımladık

    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already in use.");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);
        return new RegisterResponse("User registered successfully", user.getEmail());
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Artık jwtUtil null değil, generateToken doğru çalışacak.
        String token = jwtUtil.generateToken(user);
        return new LoginResponse(token, user.getEmail());
    }
}
