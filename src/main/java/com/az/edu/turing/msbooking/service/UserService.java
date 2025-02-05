package com.az.edu.turing.msbooking.service;


import com.az.edu.turing.msbooking.domain.entity.UserEntity;
import com.az.edu.turing.msbooking.domain.repository.UserRepository;
import com.az.edu.turing.msbooking.exception.AlreadyExistsException;
import com.az.edu.turing.msbooking.exception.NotFoundException;
import com.az.edu.turing.msbooking.mapper.UserMapper;
import com.az.edu.turing.msbooking.model.dto.request.CreateUserRequest;
import com.az.edu.turing.msbooking.model.dto.request.UpdateUserRequest;
import com.az.edu.turing.msbooking.model.dto.response.UserDto;
import com.az.edu.turing.msbooking.model.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto createUser(CreateUserRequest createUserRequest) {
        checkIfFlightExists(createUserRequest.getEmail());
        UserEntity userEntity = userMapper.toUserEntity(createUserRequest);
        UserEntity savedEntity = userRepository.save(userEntity);
        return userMapper.toUserDto(savedEntity);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toUserDto).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public UserDto updateUserById(Long id, UpdateUserRequest updateUserRequest) {
        checkIfFlightExists(updateUserRequest.getEmail());
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User with id " + id + " not found");
        }
        return userMapper.toUserDto(userRepository.save(userMapper.toUserEntity(updateUserRequest)));

    }

    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User with id " + id + " not found");
        }
        //soft-delete
        userRepository.findById(id)
                .ifPresent(userEntity -> {
                    userEntity.setStatus(UserStatus.DELETED);
                    userRepository.save(userEntity);
                });

        //hard-delete
//        userRepository.deleteById(id);

    }

    private void checkIfFlightExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new AlreadyExistsException("User already exists with email: " + email);
        }
    }
}
