package com.srishti.blogapis.services;

import java.util.List;

import com.srishti.blogapis.payloads.PostDto;

public interface PostService {
    
    PostDto createPost(PostDto postDto, Integer userId);

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);

    PostDto getPostById(Integer postId);

    List<PostDto> getAllPosts();

    List<PostDto> getAllPostsByCategory(Integer categoryId);

    List<PostDto> getAllPostsByUser(Integer userId);

    void deletePost(Integer postId);

    List<PostDto> searchPost(String keyword);
}
