package com.srishti.blogapis.services;

import java.util.List;

import com.srishti.blogapis.payloads.PostDto;
import com.srishti.blogapis.payloads.PostResponse;

public interface PostService {
    
    PostDto createPost(PostDto postDto, Integer userId);

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);

    PostDto getPostById(Integer postId);

    PostResponse getAllPosts(Integer pageNumber, Integer pageSize);

    PostResponse getAllPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize);

    PostResponse getAllPostsByUser(Integer userId, Integer pageNumber, Integer pageSize);

    void deletePost(Integer postId);

    List<PostDto> searchPost(String keyword);
}
