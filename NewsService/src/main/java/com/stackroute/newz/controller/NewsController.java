package com.stackroute.newz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.newz.model.News;
import com.stackroute.newz.model.UserNews;
import com.stackroute.newz.repository.NewsRepository;
import com.stackroute.newz.service.NewsService;
import com.stackroute.newz.util.exception.NewsNotFoundException;

/*
 * As in this assignment, we are working with creating RESTful web service, hence annotate
 * the class with @RestController annotation.A class annotated with @Controller annotation
 * has handler methods which returns a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized 
 * format. Starting from Spring 4 and above, we can use @RestController annotation which 
 * is equivalent to using @Controller and @ResposeBody annotation
 */
@RestController
@RequestMapping("/api/v1")
public class NewsController {
	
	NewsService newsService;
	//NewsRepository newsrepo;
	

	/*
	 * Autowiring should be implemented for the NewsService. (Use Constructor-based
	 * autowiring) Please note that we should not create any object using the new
	 * keyword
	 */
@Autowired	
	public NewsController(NewsService newsService) {
		this.newsService=newsService;
	}

	/*
	 * Define a handler method which will create a specific news by reading the
	 * Serialized object from request body and save the news details in the
	 * database.This handler method should return any one of the status messages
	 * basis on different situations: 
	 * 1. 201(CREATED) - If the news created successfully. 
	 * 2. 409(CONFLICT) - If the newsId conflicts with any existing user.
	 * 
	 * This handler method should map to the URL "/api/v1/news" using HTTP POST method
	 */
	@PostMapping("/news")
	public ResponseEntity<?> addusernews(@RequestBody News unews){
		
		boolean bol=newsService.addNews(unews);
		if (bol) {
			return new ResponseEntity<News>(unews,HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("News not added",HttpStatus.CONFLICT);
		}
		
		
		//return new ResponseEntity<UserNews>(unews,HttpStatus.CREATED);
	}
	/*
	
@PostMapping("/addnews")
	public ResponseEntity<?> addnews(@RequestBody News news) {
	
	boolean bol=newsserv.addNews(news);
	
	return new ResponseEntity<News>(news,HttpStatus.CREATED);
	
	
		
	}
@postMapping("/adduser")
public ResponseEntity<?> adduser(@RequestBody UserNews unews) {
	
	boolean bol=newsserv.addUser(unews);
	
	return new ResponseEntity<News>(news,HttpStatus.CREATED);
	
	
		
	}
	 * 
	 */
	/*
	 * Define a handler method which will delete a news from a database.
	 * This handler method should return any one of the status messages basis 
	 * on different situations: 
	 * 1. 200(OK) - If the news deleted successfully from database. 
	 * 2. 404(NOT FOUND) - If the news with specified newsId is not found.
	 *
	 * This handler method should map to the URL "/api/v1/news/{userId}/{newsId}" 
	 * using HTTP Delete method where "userId" should be replaced by a valid userId 
	 * without {} and "newsId" should be replaced by a valid newsId 
	 * without {}.
	 * 
	 */
@DeleteMapping("/news/{userId}/{newsId}")
public ResponseEntity<?> deletenews(@PathVariable("userId") String userId, @PathVariable("newsId") int newsId) {
	if(newsService.deleteNews(userId, newsId)) {
		return new ResponseEntity<String>("Deleted", HttpStatus.OK);
	}else
	{
		return new ResponseEntity<String>("News not found", HttpStatus.NOT_FOUND);
	}
	
	
}


	/*
	 * Define a handler method which will delete all the news of a specific user from 
	 * a database. This handler method should return any one of the status messages 
	 * basis on different situations: 
	 * 1. 200(OK) - If the newsId deleted successfully from database. 
	 * 2. 404(NOT FOUND) - If the note with specified newsId is not found.
	 *
	 * This handler method should map to the URL "/api/v1/news/{userId}" 
	 * using HTTP Delete method where "userId" should be replaced by a valid userId 
	 * without {} and "newsid" should be replaced by a valid newsId 
	 * without {}.
	 * 
	 */
@DeleteMapping("/news/{userId}")
public ResponseEntity<?> deleteallnews(@PathVariable("userId") String uId) throws NewsNotFoundException
{
	try {
		newsService.deleteAllNews(uId);
		return new ResponseEntity<String>("Deleted all News",HttpStatus.OK);
		
	} catch (NewsNotFoundException e) {
	 
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		
	}	
	
}
	
	/*
	 * Define a handler method which will update a specific news by reading the
	 * Serialized object from request body and save the updated news details in a
	 * database. 
	 * This handler method should return any one of the status messages
	 * basis on different situations: 
	 * 1. 200(OK) - If the news updated successfully.
	 * 2. 404(NOT FOUND) - If the news with specified newsId is not found.
	 * 
	 * This handler method should map to the URL "/api/v1/news/{userId}/{newsId}" using 
	 * HTTP PUT method where "userId" should be replaced by a valid userId 
	 * without {} and "newsid" should be replaced by a valid newsId without {}.
	 * 
	 */
@PutMapping("/news/{userId}/{newsId}")
public ResponseEntity<?> updatenews(@RequestBody News unews, @PathVariable("userId") String uId, @PathVariable("newsId") int nId) throws NewsNotFoundException{
	
//	try {
//		News news=newsService.getNewsByNewsId(uId, nId);
		try {
			newsService.updateNews(unews, nId, uId);
			return new ResponseEntity<String>("Updated the news",HttpStatus.OK);
		}catch(NewsNotFoundException e) {
		 
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
		
//		} catch(NewsNotFoundException e) {
//			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
//		}
	
}
	
	/*
	 * Define a handler method which will get us the specific news by a userId.
	 * This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If the news found successfully. 
	 * 2. 404(NOT FOUND) - If the news with specified newsId is not found.
	 * 
	 * This handler method should map to the URL "/api/v1/news/{userId}/{newsId}" 
	 * using HTTP GET method where "userId" should be replaced by a valid userId 
	 * without {} and "newsid" should be replaced by a valid newsId without {}.
	 * 
	 */
	@GetMapping("/news/{userId}/{newsId}")
	public ResponseEntity<?> findnewsbyuserId(@PathVariable("userId") String uId, @PathVariable("newsId") Integer nId) throws NewsNotFoundException
	{
		try {
			News news=newsService.getNewsByNewsId(uId, nId);
			
				return new ResponseEntity<News>(news,HttpStatus.OK);
			}catch(NewsNotFoundException e) {
			 
				return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
			}
			
		
	}

	/*
	 * Define a handler method which will show details of all news created by specific 
	 * user. This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If the news found successfully. 
	 * 2. 404(NOT FOUND) - If the news with specified newsId is not found.
	 * This handler method should map to the URL "/api/v1/news/{userId}" using HTTP GET method
	 * where "userId" should be replaced by a valid userId without {}.
	 * 
	 */

@GetMapping("/news/{userId}")
public ResponseEntity<?> findallnews(@PathVariable("userId") String uId) throws NewsNotFoundException
{
	List<News> news=newsService.getAllNewsByUserId(uId);
	
	if (news==null) {
		//throw new NewsNotFoundException("News not found");
		return new ResponseEntity<String>("News Not Found",HttpStatus.NOT_FOUND);
	}
		return new ResponseEntity<List<News>>(news,HttpStatus.OK);
		
}

}
