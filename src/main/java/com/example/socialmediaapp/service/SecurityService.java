package com.example.socialmediaapp.service;

import com.example.socialmediaapp.dao.entities.RoleEntity;
import com.example.socialmediaapp.dao.entities.UserEntity;
import com.example.socialmediaapp.dao.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userEntity = userRepository.getUserEntityByUsername(username);
        var user = new User(
                userEntity.getUsername(),
                userEntity.getPasswd(),
                getAuthorities(userEntity)
        );

        return user;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(UserEntity userEntity){
        List<RoleEntity> roles = userEntity.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (RoleEntity role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }

        return authorities;
    }
}
