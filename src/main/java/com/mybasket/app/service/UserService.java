package com.mybasket.app.service;

import com.mybasket.app.dto.UserDto;
import com.mybasket.app.entity.User;
import com.mybasket.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {


    UserDto createUser(UserDto userDto);

    UserDto updateUser(Integer userId, UserDto userDto);

    void deleteUser(Integer userId);

    List<UserDto> getAllUsers();

    UserDto getUser(Integer userId);

}
