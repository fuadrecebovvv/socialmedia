package com.example.socialmediaapp.service

import com.example.socialmediaapp.dao.entities.PostEntity
import com.example.socialmediaapp.dao.entities.UserEntity
import com.example.socialmediaapp.dao.repositories.PostRepository
import com.example.socialmediaapp.dao.repositories.UserRepository
import com.example.socialmediaapp.mapper.PostMapper
import com.example.socialmediaapp.model.PostDto
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class PostServiceTest extends Specification {

    private PostMapper postMapper
    private PostRepository postRepository
    private UserRepository userRepository
    private PostService postService
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    void setup() {
        postMapper = Mock()
        postRepository = Mock()
        userRepository = Mock()
        postService = new PostService(postMapper, postRepository, userRepository)
    }

    def "AddPost"() {
        given:
        def id = random.nextLong()
        def postDto = random.nextObject(PostDto)
        def userEntity = random.nextObject(UserEntity)
        def postEntity = random.nextObject(PostEntity)

        when: postService.addPost(id, postDto)

        then:
        1 * userRepository.findById(id) >> Optional.of(userEntity)
        1 * postMapper.mapToEntity(postDto) >> postEntity
        1 * postRepository.save(postEntity)
    }

    def "AllPosts"() {
        given:
        def id = random.nextLong()
        def userEntity = Mock(UserEntity)

        def postEntity1 = random.nextObject(PostEntity)
        def postEntity2 = random.nextObject(PostEntity)
        def postEntityList = [postEntity1, postEntity2]

        def postDto1 = random.nextObject(PostDto)
        def postDto2 = random.nextObject(PostDto)
        def postDtoList = [postDto1, postDto2]

        when: def result = postService.allPosts(id)

        then:
        1 * userRepository.findById(id) >> Optional.of(userEntity)
        1 * userEntity.getPosts() >> postEntityList
        1 * postMapper.mapToDto(postEntity1) >> postDto1
        1 * postMapper.mapToDto(postEntity2) >> postDto2
        result == postDtoList
    }

    def "DeletePost"() {
        given:
        def id = random.nextLong()

        when: postService.deletePost(id)

        then: 1 * postRepository.deleteById(id)
    }

    def "HowManyLikes"() {
        given:
        def id = random.nextLong()
        def postEntity = random.nextObject(PostEntity)

        when: def result = postService.howManyLikes(id)

        then:
        1 * postRepository.findById(id) >> Optional.of(postEntity)
        1 * postEntity.getLikeCount() == result
    }

    def "RecentPosts"() {
        given:
        def followingId1 = random.nextLong()
        def followingId2 = random.nextLong()
        def followingsList = [followingId1, followingId2]

        def postEntity1 = random.nextObject(PostEntity)
        def postEntity2= random.nextObject(PostEntity)
        def postEntityList = [postEntity1, postEntity2]

        def postDto1 = random.nextObject(PostDto)
        def postDto2= random.nextObject(PostDto)
        def postDtoList = [postDto1, postDto2]


        when: def result = postService.recentPosts(followingsList)

        then:
        1 * postRepository.recentPosts(followingsList) >> postEntityList
        1 * postMapper.mapToDto(postEntity1) >> postDto1
        1 * postMapper.mapToDto(postEntity2) >> postDto2
        result == postDtoList
    }
}
