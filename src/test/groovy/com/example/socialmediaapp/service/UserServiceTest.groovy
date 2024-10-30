package com.example.socialmediaapp.service

import com.example.socialmediaapp.dao.entities.UserEntity
import com.example.socialmediaapp.dao.repositories.UserRepository
import com.example.socialmediaapp.mapper.UserMapper
import com.example.socialmediaapp.model.UserDto
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class UserServiceTest extends Specification {
    private UserMapper userMapper
    private UserRepository userRepository
    private UserService userService
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    void setup() {
        userMapper = Mock()
        userRepository = Mock()
        userService = new UserService(userMapper, userRepository)
    }

    def "GetUsers"() {
        given:
        def userEntity1 = random.nextObject(UserEntity)
        def userEntity2 = random.nextObject(UserEntity)
        def userEntityList = [userEntity1, userEntity2]

        def userDto1 = random.nextObject(UserDto)
        def userDto2 = random.nextObject(UserDto)
        def userDtoList = [userDto1, userDto2]

        when:
        def result = userService.getUsers()

        then:
        1 * userRepository.findAll() >> userEntityList
        1 * userMapper.mapToDto(userEntity1) >> userDto1
        1 * userMapper.mapToDto(userEntity2) >> userDto2

        userDtoList == result
    }

    def "AddUser"() {
        given:
        def userDto = random.nextObject(UserDto)
        def userEntity = random.nextObject(UserEntity)

        when: userService.addUser(userDto)

        then:
        1 * userMapper.mapToEntity(userDto) >> userEntity
        1 * userRepository.save(userEntity)

    }

    def "UpdateUser"() {
        given:
        def id = random.nextLong()
        def userDto = random.nextObject(UserDto)
        def userEntity = random.nextObject(UserEntity)
        userEntity.setPosts(null)

        when:
        userService.updateUser(id, userDto)

        then:
        1 * userRepository.findById(id) >> Optional.of(userEntity)
        1 * userMapper.mapToEntity(userDto, userEntity)
        1 * userRepository.save(userEntity)
    }

    def "DeleteUser"() {
        given:
        def id = random.nextLong()
        when: userService.deleteUser(id)
        then:
        1 * userRepository.deleteById(id)
    }
}
