package com.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.CommentDto;
import com.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/blogs/comment")
@Validated
public class CommentController {
	
    private final CommentService commentService;
    public CommentController(CommentService commentService) {
    	this.commentService =commentService;
    }
    
    @PostMapping
    public CommentDto addComment(@Valid @RequestBody CommentDto commentDto) {
        return commentService.addComment(commentDto);
    }
    @GetMapping("/{blogId}")
    public ResponseEntity<?> getCommentsByBlogId(@PathVariable Long blogId) {
        return commentService.getCommentsByBlogId(blogId);
    }

}