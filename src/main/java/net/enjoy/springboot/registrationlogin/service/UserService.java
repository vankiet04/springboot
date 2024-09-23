package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.UserDto;
import net.enjoy.springboot.registrationlogin.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User getUser(Long id);

    User updateUser(Long id, User user, boolean isPasswordChanged);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}