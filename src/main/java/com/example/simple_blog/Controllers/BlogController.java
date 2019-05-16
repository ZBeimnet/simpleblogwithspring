package com.example.simple_blog.Controllers;

import com.example.simple_blog.Models.Blog;
import com.example.simple_blog.Repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    BlogRepository blogRepository;

    @GetMapping("/")
    public String showBlogs(Model model) {
        Iterable<Blog> allBlogs = blogRepository.findAll();
        Optional<Blog> featuredBlog = blogRepository.findById(1);

        model.addAttribute("blogs", allBlogs);
        model.addAttribute("featuredBlog", featuredBlog.get());

        return "blogs";
    }

    @GetMapping("/post")
    public String showPostForm(Model model) {
        Blog blog = new Blog();
        model.addAttribute("blog", blog);

        return "post";
    }

    @PostMapping("/post")
    public String post(@Valid Blog blog, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "post";
        }
        else {
            blogRepository.save(blog);
            model.addAttribute("successMessage", "Blog Successfully Posted!");
            model.addAttribute("blog", new Blog());
            return "post";
        }

    }
}
