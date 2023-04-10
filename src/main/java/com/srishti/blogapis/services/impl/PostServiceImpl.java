package com.srishti.blogapis.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.srishti.blogapis.exceptions.ResourceNotFoundException;
import com.srishti.blogapis.models.Category;
import com.srishti.blogapis.models.Post;
import com.srishti.blogapis.models.User;
import com.srishti.blogapis.payloads.PostDto;
import com.srishti.blogapis.payloads.PostResponse;
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
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Post> pages = this.postRepo.findAll(pageable);
        PostResponse response = this.getPostResponse(pages);
        return response;
    }

    @Override
    public PostResponse getAllPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Post> pages = this.postRepo.findByCategory(category, pageable);
        PostResponse response = this.getPostResponse(pages);
        return response;
    }

    @Override
    public PostResponse getAllPostsByUser(Integer userId, Integer pageNumber, Integer pageSize) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Post> pages = this.postRepo.findByUser(user, pageable);
        PostResponse response = this.getPostResponse(pages);
        return response;
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

    private PostResponse getPostResponse(Page<Post> pages) {
        List<Post> posts = pages.getContent();
        List<PostDto> postDtos = posts.stream().map(post -> this.entityToDto(post))
                .collect(Collectors.toList());

        PostResponse response = new PostResponse();
        response.setContent(postDtos);
        response.setPageNumber(pages.getNumber());
        response.setPageSize(pages.getSize());
        response.setTotalElements(pages.getTotalElements());
        response.setTotalPages(pages.getTotalPages());
        response.setLastPage(pages.isLast());

        return response;
    }
    
}
