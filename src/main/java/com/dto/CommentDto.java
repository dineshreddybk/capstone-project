package com.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CommentDto {
	
	private Long id;
	
	@NotNull(message="Blog ID cannot be null")
	private Long blogId;
	
	@NotEmpty(message = "Comment cannot be empty")
	@Size(min = 3, max = 200, message = "Comment must be between 3 and 200 characters")
	private String comment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public CommentDto(Long id,Long blogId,String comment) {
		super();
		this.id = id;
		this.blogId = blogId;
		this.comment = comment;
	}

	public CommentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
