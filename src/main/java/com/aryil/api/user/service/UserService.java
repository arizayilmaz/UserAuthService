package com.aryil.api.user.service;

import com.aryil.api.user.dto.UserDto;
import com.aryil.api.user.entity.User;
import com.aryil.api.user.mapper.UserMapper;
import com.aryil.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDto createUser(User user) {
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }


}
