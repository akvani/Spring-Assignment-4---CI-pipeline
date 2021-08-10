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
	Integer NewsSourceId;
	String NewsSourceName;
	String NewsSourceDesc;
	String NewsSourceCreatedBy;
	LocalDateTime NewsSourceCreationDate;
	
	
	
	public NewsSource(Integer NewsSourceId,String NewsSourceName,	String NewsSourceDesc,String NewsSourceCreatedBy,LocalDateTime NewsSourceCreationDate)
	{
		this.NewsSourceId=NewsSourceId;
		this.NewsSourceName=NewsSourceName;
		this.NewsSourceDesc=NewsSourceDesc;
		this.NewsSourceCreatedBy=NewsSourceCreatedBy;
		this.NewsSourceCreationDate=LocalDateTime.now();
	}
	public NewsSource() {
		
	}
	public Integer getNewsSourceId() {
		return NewsSourceId;
	}
	public void setNewsSourceId(Integer newsSourceId) {
		NewsSourceId = newsSourceId;
	}
	public String getNewsSourceName() {
		return NewsSourceName;
	}
	public void setNewsSourceName(String newsSourceName) {
		NewsSourceName = newsSourceName;
	}
	public String getNewsSourceDesc() {
		return NewsSourceDesc;
	}
	public void setNewsSourceDesc(String newsSourceDesc) {
		NewsSourceDesc = newsSourceDesc;
	}
	public String getNewsSourceCreatedBy() {
		return NewsSourceCreatedBy;
	}
	public void setNewsSourceCreatedBy(String newsSourceCreatedBy) {
		NewsSourceCreatedBy = newsSourceCreatedBy;
	}
	public LocalDateTime getNewsSourceCreationDate() {
		return NewsSourceCreationDate;
	}
	public void setNewsSourceCreationDate( ) {
		NewsSourceCreationDate = LocalDateTime.now();
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
