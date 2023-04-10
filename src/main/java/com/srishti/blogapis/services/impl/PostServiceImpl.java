package com.srishti.blogapis.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srishti.blogapis.exceptions.ResourceNotFoundException;
import com.srishti.blogapis.models.Category;
import com.srishti.blogapis.models.Post;
import com.srishti.blogapis.models.User;
import com.srishti.blogapis.payloads.PostDto;
import com.srishti.blogapis.repositories.CategoryRepo;
import com.srishti.blogapis.repositories.PostRepo;
import com.srishti.blogapis.repositories.UserRepo;
import com.srishti.blogapis.services.PostService;

@Service
public class PostServiceImpl implements PostService{
    
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        Post post = this.dtoToEntity(postDto);
        post.setUser(user);
        post.setDateCreated(new Date());
        Post savedPost = this.postRepo.save(post);

        return this.entityToDto(savedPost);
    }

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        Post post = this.dtoToEntity(postDto);
        post.setUser(user);
        post.setCategory(category);
        post.setDateCreated(new Date());
        Post savedPost = this.postRepo.save(post);

        return this.entityToDto(savedPost);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImage(postDto.getImage());
        Post updatedPost = this.postRepo.save(post);

        return this.entityToDto(updatedPost);
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        return this.entityToDto(post);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = this.postRepo.findAll();
        List<PostDto> postDtos = posts.stream().map(post -> this.entityToDto(post))
                .collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> getAllPostsByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        List<Post> posts = this.postRepo.findByCategory(category);
        List<PostDto> postDtos = posts.stream().map(post -> this.entityToDto(post))
                .collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> getAllPostsByUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDto> postDtos = posts.stream().map(post -> this.entityToDto(post))
                .collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        this.postRepo.delete(post);
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchPost'");
    }

    private Post dtoToEntity(PostDto postDto) {
        return this.modelMapper.map(postDto, Post.class);
    }

    private PostDto entityToDto(Post post) {
        return this.modelMapper.map(post, PostDto.class);
    }
    
}
