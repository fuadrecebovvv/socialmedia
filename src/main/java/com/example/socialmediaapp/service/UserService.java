package com.example.socialmediaapp.service;


import com.example.socialmediaapp.dao.entities.RoleEntity;
import com.example.socialmediaapp.dao.repositories.UserRepository;
import com.example.socialmediaapp.enums.ProfileType;
import com.example.socialmediaapp.mapper.UserMapper;
import com.example.socialmediaapp.model.OTPGenerator;
import com.example.socialmediaapp.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public List<UserDto> getUsers(){
        log.info("UserService.getUsers.start");
        List<UserDto> userList = userRepository
                                    .findAll()
                                    .stream()
                                    .map(userMapper::mapToDto)
                                    .toList();
        log.info("UserService.getUsers.end");
        return userList;
    }

    public void addUser(UserDto userDto){
        log.info("UserService.addUser.start");
        userDto.setPasswd(passwordEncoder.encode(userDto.getPasswd()));
        var userEntity = userMapper.mapToEntity(userDto);
        var roles = new RoleEntity(null, "ROLE_USER", userEntity);
        userEntity.setRoles(List.of(roles));
        userEntity.setProfileType(ProfileType.PUBLIC);
        userRepository.save(userEntity);
        String otp = OTPGenerator.generateOTP(6);
        userEntity.getOtps().add(otp);
        emailService.sendSimpleEmail(
                userDto.getEmail(), "Confirmation", "Welcome to the FakeSocialMedia\n" +
                        "This is your confirmation code: " + otp
        );
        log.info("UserService.addUser.end");
    }

    public void changeProfileType(Long id){
        log.info("UserService.changeProfileType.start userId: {}", id);
        var userEntity = userRepository.findById(id).orElseThrow();
        if(userEntity.getProfileType() == ProfileType.PUBLIC){
            userEntity.setProfileType(ProfileType.PRIVATE);
        } else if (userEntity.getProfileType() == ProfileType.PRIVATE){
            userEntity.setProfileType(ProfileType.PUBLIC);
        }
        userRepository.save(userEntity);
        log.info("UserService.changeProfileType.end userId: {}", id);
    }
    
    public void updateUser(Long id, UserDto userDto){
        log.info("UserService.updateUser.start userId: {}", id);
        var userEntity = userRepository.findById(id).orElseThrow();
        userMapper.mapToEntity(userDto, userEntity);
        userRepository.save(userEntity);
        log.info("UserService.updateUser.end userId: {}", id);
    }

    public void deleteUser(Long id){
        log.info("UserService.deleteUser.start userId: {}", id);
        userRepository.deleteById(id);
        log.info("UserService.deleteUser.end userId: {}", id);
    }


}
