package com.srishti.blogapis.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srishti.blogapis.payloads.CommentDto;
import com.srishti.blogapis.services.CommentService;

@RestController
@RequestMapping("api/")
public class CommentController {

    @Autowired
    private CommentService commentService;
    
    @PostMapping("post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId) {
        CommentDto createCommentDto = this.commentService.createComment(commentDto, postId);
        return new ResponseEntity<CommentDto>(createCommentDto, HttpStatus.CREATED);
    }

    @DeleteMapping("comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer commentId) {
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(Map.of("message", "Comment deleted successfully"), HttpStatus.OK);
    }
}
