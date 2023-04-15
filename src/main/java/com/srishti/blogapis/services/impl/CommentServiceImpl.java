package com.srishti.blogapis.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srishti.blogapis.exceptions.ResourceNotFoundException;
import com.srishti.blogapis.models.Comment;
import com.srishti.blogapis.models.Post;
import com.srishti.blogapis.payloads.CommentDto;
import com.srishti.blogapis.repositories.CommentRepo;
import com.srishti.blogapis.repositories.PostRepo;
import com.srishti.blogapis.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
    
    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));
        Comment comment = this.dtoToEntity(commentDto);
        comment.setPost(post);
        Comment savedComment = this.commentRepo.save(comment);

        return this.entityToDto(savedComment);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("comment", "id", commentId));
        this.commentRepo.delete(comment);
    }
    
    private Comment dtoToEntity(CommentDto commentDto) {
        return this.modelMapper.map(commentDto, Comment.class);
    }

    private CommentDto entityToDto(Comment comment) {
        return this.modelMapper.map(comment, CommentDto.class);
    }
}
