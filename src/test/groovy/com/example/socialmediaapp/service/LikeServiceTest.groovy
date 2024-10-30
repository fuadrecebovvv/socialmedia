package com.example.socialmediaapp.service

import com.example.socialmediaapp.dao.entities.LikeEntity
import com.example.socialmediaapp.dao.entities.PostEntity
import com.example.socialmediaapp.dao.entities.UserEntity
import com.example.socialmediaapp.dao.repositories.LikeRepository
import com.example.socialmediaapp.dao.repositories.PostRepository
import com.example.socialmediaapp.dao.repositories.UserRepository
import com.example.socialmediaapp.mapper.LikeMapper
import com.example.socialmediaapp.model.LikeDto
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class LikeServiceTest extends Specification {
    private LikeMapper likeMapper
    private LikeRepository likeRepository
    private UserRepository userRepository
    private PostRepository postRepository
    private LikeService likeService
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    void setup() {
        likeMapper = Mock()
        likeRepository = Mock()
        userRepository = Mock()
        postRepository = Mock()
        likeService = new LikeService(likeMapper, likeRepository, userRepository, postRepository)
    }

    def "AddLike"() {
        given:
        def likeDto = random.nextObject(LikeDto)
        def userEntity = random.nextObject(UserEntity)
        def postEntity = random.nextObject(PostEntity)
        def likeEntity = random.nextObject(LikeEntity)

        when: likeService.addLike(likeDto)

        then:
        1 * userRepository.findById(likeDto.getUserId()) >> Optional.of(userEntity)
        1 * postRepository.findById(likeDto.getPostId()) >> Optional.of(postEntity)
        1 * likeMapper.toEntity(likeDto) >> likeEntity
    }

    def "DeleteLike"() {
    }

    def "AllLikes"() {
    }

    def "GetLikesByPost"() {
    }
}
