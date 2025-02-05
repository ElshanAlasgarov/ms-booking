package com.az.edu.turing.msbooking.mapper;

import com.az.edu.turing.msbooking.domain.entity.UserEntity;
import com.az.edu.turing.msbooking.model.dto.request.CreateUserRequest;
import com.az.edu.turing.msbooking.model.dto.request.UpdateUserRequest;
import com.az.edu.turing.msbooking.model.dto.response.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "email", source = "email")
    UserEntity toUserEntity(CreateUserRequest request);

    UserEntity toUserEntity(UpdateUserRequest request);

    UserDto toUserDto(UserEntity user);
}
