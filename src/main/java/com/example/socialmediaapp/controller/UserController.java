package com.example.socialmediaapp.controller;

import com.example.socialmediaapp.model.UserDto;
import com.example.socialmediaapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    public final UserService userService;

    @PostMapping
    public void addUser(@Valid @RequestBody UserDto userDto){
        userService.addUser(userDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }

    @PutMapping("/{userId}")
    public void updateUser(@PathVariable Long userId, @RequestBody UserDto userDto){
        userService.updateUser(userId, userDto);
    }

    @PutMapping("/userId")
    public void changeProfileType(@PathVariable Long userId){
        userService.changeProfileType(userId);
    }

    @GetMapping
    public List<UserDto> getUsers(){
        return userService.getUsers();
    }
}
