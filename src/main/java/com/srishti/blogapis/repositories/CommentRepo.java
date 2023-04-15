package com.srishti.blogapis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srishti.blogapis.models.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{
    
}
