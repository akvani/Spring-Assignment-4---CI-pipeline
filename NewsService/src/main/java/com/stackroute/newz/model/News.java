package com.stackroute.newz.model;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;

public class News {
	@Id
	Integer newsId;
	String title;
	String author;
	String description;	
	@JsonSerialize
	LocalDateTime publishedAt;
	String content;
	String url;
	String urlToImage;
	 Reminder Reminder;
	  NewsSource Newssource;
	
	public Integer getNewsId() {
		return newsId;
	}
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getPublishedAt() {
		return publishedAt;
	}
	public void setPublishedAt() {
		this.publishedAt = LocalDateTime.now();
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrlToImage() {
		return urlToImage;
	}
	public void setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
	}
	public Reminder getReminder() {
		return Reminder;
	}
	public void setReminder(Reminder reminder) {
		Reminder = reminder;
	}
	public NewsSource getNewssource() {
		return Newssource;
	}
	public void setNewssource(NewsSource newssource) {
		Newssource = newssource;
	}
	 

	
	/*
	 * This class should have ten fields
	 * (newsId,title,author,description,publishedAt,content,url,urlToImage,Reminder,
	 * NewsSource). This class should also contain the getters and setters for the 
	 * fields along with the no-arg , parameterized	constructor and toString method.
	 * The value of publishedAt should not be accepted from the user but should be
	 * always initialized with the system date.
	 */
	 

}
