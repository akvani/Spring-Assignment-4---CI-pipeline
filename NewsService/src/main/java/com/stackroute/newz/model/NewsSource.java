package com.stackroute.newz.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * Please note that this class is annotated with @Document annotation
 * @Document identifies a domain object to be persisted to MongoDB.
 *  
 */
@Document
public class NewsSource {
	@Id
	Integer newsSourceId;
	String newsSourceName;
	String newsSourceDesc;
	String newsSourceCreatedBy; //user Id
	LocalDateTime newsSourceCreationDate;
	
	public NewsSource(int newssourceId,String newssourceName,	String newssourceDesc,	String newssourceCreatedBy,	LocalDateTime newssourceCreationDate)
	{
		this.newsSourceId=newssourceId;
		this.newsSourceName=newssourceName;
		this.newsSourceDesc=newssourceDesc;
		this.newsSourceCreatedBy=newssourceCreatedBy;
		this.newsSourceCreationDate=LocalDateTime.now();
		
		
	}
	public NewsSource()
	{
		this.newsSourceCreationDate=LocalDateTime.now();
	}
	public Integer getNewsSourceId() {
		return newsSourceId;
	}
	public void setNewsSourceId(Integer newsSourceId) {
		this.newsSourceId = newsSourceId;
	}
	public String getNewsSourceName() {
		return newsSourceName;
	}
	public void setNewsSourceName(String newsSourceName) {
		this.newsSourceName = newsSourceName;
	}
	public String getNewsSourceDesc() {
		return newsSourceDesc;
	}
	public void setNewsSourceDesc(String newsSourceDesc) {
		this.newsSourceDesc = newsSourceDesc;
	}
	public String getNewsSourceCreatedBy() {
		return newsSourceCreatedBy;
	}
	public void setNewsSourceCreatedBy(String newsSourceCreatedBy) {
		this.newsSourceCreatedBy = newsSourceCreatedBy;
	}
	public LocalDateTime getNewsSourceCreationDate() {
		return newsSourceCreationDate;
	}
	public void setNewsSourceCreationDate() {
		this.newsSourceCreationDate = LocalDateTime.now();
	}
	
	

	/*
	 * This class should have five fields (newssourceId,newssourceName,
	 * newssourceDesc,newssourceCreatedBy,newssourceCreationDate). Out of these five fields, 
	 * the field newssourceId should be annotated with @Id (This annotation explicitly 
	 * specifies the document identifier). This class should also contain the getters and 
	 * setters for the fields, along with the no-arg , parameterized constructor and toString
	 * method.The value of newssourceCreationDate should not be accepted from the user but
	 * should be always initialized with the system date.
	 */

	


}
