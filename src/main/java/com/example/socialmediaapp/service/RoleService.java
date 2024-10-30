package com.example.socialmediaapp.service;

import com.example.socialmediaapp.dao.entities.RoleEntity;
import com.example.socialmediaapp.dao.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleService {
    private final UserRepository userRepository;

    public void setAdmin(String username){
        log.info("RoleService.setAdmin.start");
        var user = userRepository.getUserEntityByUsername(username);
        var role = new RoleEntity(null, "ROLE_ADMIN", user);
        user.getRoles().add(role);
        userRepository.save(user);
        log.info("RoleService.setAdmin.end");
    }
}
