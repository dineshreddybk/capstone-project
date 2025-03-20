package com.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.dto.BlogDto;
import com.entity.BlogEntity;
import com.exception.ResourceNotFoundException;
import com.repository.BlogRepository;

@Service
public class BlogService {
	 
	 private final BlogRepository blogRepository;
	 
	 public BlogService(BlogRepository blogRepository) {
		 this.blogRepository =blogRepository;
	 }

	 public BlogDto createBlog(BlogDto blogDto) {
		 BlogEntity blog = new BlogEntity(blogDto.getTitle(), blogDto.getContent());
		 blog = blogRepository.save(blog);
		 return new BlogDto(blog.getId(), blog.getTitle(), blog.getContent());
	    }
	    
	public BlogDto getBlogById(Long id) {
	    BlogEntity blog = blogRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Blog with ID " + id + " not found"));
	    return new BlogDto(blog.getId(), blog.getTitle(), blog.getContent());
	}

	public BlogDto updateBlog(Long id, BlogDto blogDto) {
	    BlogEntity blog = blogRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Blog with ID " + id + " not found"));
	    blog.setTitle(blogDto.getTitle());
	    blog.setContent(blogDto.getContent());
	    blogRepository.save(blog);
	    return new BlogDto(blog.getId(), blog.getTitle(), blog.getContent());
	}

	public void deleteBlog(Long id) {
	    if (!blogRepository.existsById(id)) {
	        throw new ResourceNotFoundException("Blog with ID " + id + " not found");
	    }
	    blogRepository.deleteById(id);
	}
	public List<BlogDto> getAllBlogs() {
	    List<BlogDto> blogs = blogRepository.findAll().stream()
	            .map(blog -> new BlogDto(blog.getId(), blog.getTitle(), blog.getContent()))
	            .collect(Collectors.toList());

	    if (blogs.isEmpty()) {
	        throw new ResourceNotFoundException("No blogs available");
	    }

	    return blogs;
	}
}