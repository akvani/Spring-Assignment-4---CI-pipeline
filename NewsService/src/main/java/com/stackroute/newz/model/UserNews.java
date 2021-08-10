package com.stackroute.newz.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * Please note that this class is annotated with @Document annotation
 * @Document identifies a domain object to be persisted to MongoDB.
 * 
 */
@Document
public class UserNews {
	@Id
	String userId;
	List<News> newslist;
	public UserNews(String newssourceCreatedBy, List<News> newslist2) {
		this.userId=newssourceCreatedBy;
		this.newslist=newslist2;
		// TODO Auto-generated constructor stub
	}
	public UserNews()
	{
		
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<News> getNewslist() {
		return newslist;
	}
	public void setNewslist(List<News> newslist) {
		this.newslist = newslist;
	}

	/*
	 * This class should have two fields (userId, newslist).Out of these two fields,
	 * the field userId should be annotated with @Id. This class should also contain
	 * the getters and setters for the fields.
	 */

	
}
