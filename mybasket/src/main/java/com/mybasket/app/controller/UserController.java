package com.mybasket.app.controller;


import com.mybasket.app.dto.UserDto;
import com.mybasket.app.entity.User;
import com.mybasket.app.repository.UserRepository;
import com.mybasket.app.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private  final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){

        UserDto user = (UserDto) userService.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public  ResponseEntity<List<UserDto>> getAll(){

        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }


   @GetMapping("{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer userId){
       return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
   }

    @PreAuthorize("hasRole('NORMAL')")
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer userId, @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUser(userId, userDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
