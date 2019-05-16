package com.example.simple_blog.Controllers;

import com.example.simple_blog.Models.Blog;
import com.example.simple_blog.Repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

        if(featuredBlog.isPresent()) {
            model.addAttribute("featuredBlog", featuredBlog.get());
        }
        model.addAttribute("blogs", allBlogs);


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

    @GetMapping("/post/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Optional<Blog> blog = blogRepository.findById(id);

        model.addAttribute("blog", blog);
        model.addAttribute("blogId", id);

        return "edit";
    }

    @PutMapping("/post/edit/{id}")
    public String editPost(@Valid Blog blog, BindingResult bindingResult, @PathVariable int id , Model model) {
        if(bindingResult.hasErrors()) {
            return "edit";
        }
        else {
            Blog b = blogRepository.findById(id).get();
            b.setTitle(blog.getTitle());
            b.setName(blog.getName());
            b.setBody(blog.getBody());

            blogRepository.save(b);

            model.addAttribute("successMessage", "Blog Successfully Edited!");

            return "edit";

        }

    }

    @DeleteMapping("/blog/{id}")
    public String deleteBlog(@PathVariable int id) {
        blogRepository.deleteById(id);

        return "redirect:/";
    }

}

