package com.example.simple_blog.Models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@Table(name = "blog")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotNull(message = "*Please provide your name")
    @Size(min = 3, message="Name must be greater than 3 characters")
    private String name;

    @Column(name = "title")
    @NotNull(message = "*Please provide a title")
    @Size(min = 3, message="Title must be greater than 3 characters")
    private String title;

    @Column(name = "body")
    @NotNull(message = "*Please write something")
    @Size(min = 5, message="Minimum number of characters allowed is 5")
    private String body;

    @Column(name = "post_date")
    private Date postDate;

    @PrePersist
    void postDate() {
        this.postDate = new Date();
    }
}
