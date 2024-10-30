package com.example.socialmediaapp.controller;

import com.example.socialmediaapp.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public void setAdmin(@RequestParam String username){
        roleService.setAdmin(username);
    }
}
