package com.srishti.blogapis.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srishti.blogapis.payloads.PostDto;
import com.srishti.blogapis.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId) {
        PostDto createPostdDto = this.postService.createPost(postDto, userId);

        return new ResponseEntity<>(createPostdDto, HttpStatus.CREATED);
    }

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId,
            @PathVariable Integer categoryId) {
        PostDto createPostdDto = this.postService.createPost(postDto, userId, categoryId);

        return new ResponseEntity<>(createPostdDto, HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        return ResponseEntity.ok(this.postService.getPostById(postId));
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(this.postService.getAllPosts());
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getAllPostsByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(this.postService.getAllPostsByUser(userId));
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getAllPostsByCategory(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(this.postService.getAllPostsByCategory(categoryId));
    }
    
    @PutMapping("posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable Integer postId) {
        PostDto updatePostDto = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatePostDto, HttpStatus.OK);
    }

    @DeleteMapping("posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        return new ResponseEntity<>(Map.of("message", "Post deleted successfully"), HttpStatus.OK);
    }
    
}
