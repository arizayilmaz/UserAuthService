package com.aryil.api.user.mapper;

import com.aryil.api.user.dto.UserCreateRequest;
import org.mapstruct.Mapper;
import com.aryil.api.user.entity.User;
import com.aryil.api.user.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User savedUser);
    User toUser(UserCreateRequest request);
}
