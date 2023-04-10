package com.srishti.blogapis.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srishti.blogapis.models.Category;
import com.srishti.blogapis.models.Post;
import com.srishti.blogapis.models.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
    
    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);
}
