package com.example.simple_blog.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {

    @GetMapping("/")
    public String blogs() {
        return "blogs";
    }

    @GetMapping("/post")
    public String post() {
        return "post";
    }
}
