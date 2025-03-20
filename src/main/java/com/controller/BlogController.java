package com.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.BlogDto;
import com.service.BlogService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/blogs")
@Validated
public class BlogController {
   

    private final BlogService blogService;
    public BlogController(BlogService blogService) {
    	this.blogService =blogService;
    }
    @PostMapping
    public ResponseEntity<BlogDto> createBlog(@Valid @RequestBody BlogDto blogDto) {
        return ResponseEntity.status(201).body(blogService.createBlog(blogDto));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<BlogDto> getBlogById(@PathVariable Long id) {
        return  ResponseEntity.ok(blogService.getBlogById(id));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<BlogDto> updateBlog(@PathVariable Long id,@Valid @RequestBody BlogDto blogDto) {
        return  ResponseEntity.ok(blogService.updateBlog(id, blogDto));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return  ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<List<BlogDto>> getAllBlogs() {
        return ResponseEntity.ok(blogService.getAllBlogs());
    }
}