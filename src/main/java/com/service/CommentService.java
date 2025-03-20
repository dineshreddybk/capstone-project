package com.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dto.CommentDto;
import com.entity.BlogEntity;
import com.entity.CommentEntity;
import com.exception.ResourceNotFoundException;
import com.repository.BlogRepository;
import com.repository.CommentRepository;

@Service
public class CommentService  {

    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;
    
	 public CommentService(CommentRepository commentRepository,BlogRepository blogRepository) {
		 this.blogRepository =blogRepository;
		 this.commentRepository= commentRepository;
	 }

    public CommentDto addComment(CommentDto commentDto) {
        BlogEntity blog = blogRepository.findById(commentDto.getBlogId())
                .orElseThrow(() -> new ResourceNotFoundException("Blog with ID " + commentDto.getBlogId() + " not found"));

        CommentEntity comment = new CommentEntity(blog, commentDto.getComment());
        comment = commentRepository.save(comment);

        return new CommentDto(comment.getId(), comment.getBlog().getId(), comment.getComment());
    }
    public ResponseEntity<?> getCommentsByBlogId(Long blogId) { 
        if (!blogRepository.existsById(blogId)) {
            throw new ResourceNotFoundException("Blog with ID " + blogId + " not found");
        }
        
        List<CommentEntity> comments = commentRepository.findByBlogId(blogId);

        if (comments.isEmpty()) {
            return ResponseEntity.ok(Collections.singletonMap("message", "No comments available for this blog."));
        }

        List<CommentDto> commentDtos = comments.stream()
                .map(comment -> new CommentDto(comment.getId(), comment.getBlog().getId(), comment.getComment()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(commentDtos);
    }
}