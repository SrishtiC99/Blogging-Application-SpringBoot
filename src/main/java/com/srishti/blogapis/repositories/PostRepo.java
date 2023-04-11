package com.srishti.blogapis.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.srishti.blogapis.models.Category;
import com.srishti.blogapis.models.Post;
import com.srishti.blogapis.models.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
    
    Page<Post> findByUser(User user, Pageable pageable);

    Page<Post> findByCategory(Category category, Pageable pageable);

    List<Post> findByTitleContaining(String title);
}
