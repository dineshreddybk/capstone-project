package com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="comments")
public class CommentEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name="blog_id",nullable=false)
	private BlogEntity blog;
	private String comment;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BlogEntity getBlog() {
		return blog;
	}
	public void setBlog(BlogEntity blog) {
		this.blog = blog;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public CommentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommentEntity(BlogEntity blog, String comment) {
		super();
		this.blog = blog;
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "CommentEntity [id=" + id + ", blog=" + blog + ", comment=" + comment + "]";
	}
}
