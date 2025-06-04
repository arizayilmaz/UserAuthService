package com.aryil.api.user.controller;

import com.aryil.api.exception.GeneralResponse;
import com.aryil.api.user.dto.UserCreateRequest;
import com.aryil.api.user.dto.UserDto;
import com.aryil.api.user.entity.User;
import com.aryil.api.user.mapper.UserMapper;
import com.aryil.api.user.repository.UserRepository;
import com.aryil.api.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper, UserRepository userRepository) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<GeneralResponse<Object>> createUser(@RequestBody @Valid UserCreateRequest request) {
        User user = userMapper.toUser(request);
        userService.createUser(user);
        UserDto userDto = userMapper.toDto(user);
        return ResponseEntity.ok().body(GeneralResponse.builder().data(userDto).build());
    }
    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(401).build();
        }
        String email = userDetails.getUsername(); // Biz email’i username olarak tanımladık
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserDto dto = userMapper.toDto(user);
        return ResponseEntity.ok(dto);
    }


}
