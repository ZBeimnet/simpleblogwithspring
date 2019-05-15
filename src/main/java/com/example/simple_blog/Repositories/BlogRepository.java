package com.example.simple_blog.Repositories;

import com.example.simple_blog.Models.Blog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends CrudRepository<Blog, Integer> {
}
