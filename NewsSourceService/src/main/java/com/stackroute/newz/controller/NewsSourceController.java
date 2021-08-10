package com.stackroute.newz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.newz.model.NewsSource;
import com.stackroute.newz.service.NewsSourceService;
import com.stackroute.newz.util.exception.NewsSourceNotFoundException;
import java.util.List;

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
public class NewsSourceController {

	private static final int NewsSource = 0;
	/*
	 * Autowiring should be implemented for the NewsService. (Use Constructor-based
	 * autowiring) Please note that we should not create any object using the new
	 * keyword
	 */
	NewsSourceService newssserv;
	@Autowired
	public NewsSourceController(NewsSourceService newsSourceService) {
	this.newssserv=newsSourceService;
	}
	

	/*
	 * Define a handler method which will create a specific newssource by reading the
	 * Serialized object from request body and save the newssource details in the
	 * database.This handler method should return any one of the status messages
	 * basis on different situations: 
	 * 1. 201(CREATED) - If the newssource created successfully. 
	 * 2. 409(CONFLICT) - If the newssourceId conflicts with any existing user.
	 * 
	 * This handler method should map to the URL "/api/v1/newssource" using HTTP POST method
	 */
@PostMapping("/newssource")
public ResponseEntity<?> addnews(@RequestBody NewsSource newssource)
{
	boolean bol=newssserv.addNewsSource(newssource);
	
	if (bol==true) {
		return new ResponseEntity<NewsSource>(newssource,HttpStatus.CREATED);
	}
	
	return new ResponseEntity<String>("News not added",HttpStatus.CONFLICT);
}

	/*
	 * Define a handler method which will delete a newssource from a database.
	 * This handler method should return any one of the status messages basis 
	 * on different situations: 
	 * 1. 200(OK) - If the newssource deleted successfully from database. 
	 * 2. 404(NOT FOUND) - If the newssource with specified newsId is not found.
	 *
	 * This handler method should map to the URL "/api/v1/newssource/{newssourceId}" 
	 * using HTTP Delete method where "userId" should be replaced by a valid userId 
	 * without {} and "newssourceId" should be replaced by a valid newsId 
	 * without {}.
	 * 
	 */
@DeleteMapping("/newssource/{newssourceId}")
public ResponseEntity<?> deletenewssource(@PathVariable("newsssourcId") Integer nId)
{
	boolean bol=newssserv.deleteNewsSource(nId);
	if (bol==true)
	{
		return new ResponseEntity<String>("News deleted",HttpStatus.OK);
	}
	return new ResponseEntity<String>("News not deleted",HttpStatus.NOT_FOUND);
}
	
	/*
	 * Define a handler method which will update a specific newssource by reading the
	 * Serialized object from request body and save the updated newssource details in a
	 * database. This handler method should return any one of the status messages
	 * basis on different situations: 
	 * 1. 200(OK) - If the newssource updated successfully.
	 * 2. 404(NOT FOUND) - If the newssource with specified newssourceId is not found.
	 * 
	 * This handler method should map to the URL "/api/v1/newssource/{newssourceId}" using 
	 * HTTP PUT method where "newssourceId" should be replaced by a valid newssourceId
	 * without {}.
	 * 
	 */
@PutMapping("/newssource/{newssourceId}")
public ResponseEntity<?> updateNewsource(@RequestBody NewsSource nsource, @PathVariable("neessourceId") Integer nId)
{
	String uId=nsource.getNewsSourceCreatedBy();
	
	try {
		NewsSource newssource=newssserv.getNewsSourceById(uId, nId);
		NewsSource unewssource = newssserv.updateNewsSource(newssource, nId);
		
		return new ResponseEntity<NewsSource>(unewssource,HttpStatus.OK);
	} catch (NewsSourceNotFoundException e) {
		// TODO Auto-generated catch block
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
	}

}
	
	/*
	 * Define a handler method which will get us the specific newssource by a userId.
	 * This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If the newssource found successfully. 
	 * 2. 404(NOT FOUND) - If the newssource with specified newsId is not found.
	 * 
	 * This handler method should map to the URL "/api/v1/newssource/{userId}/{newssourceId}" 
	 * using HTTP GET method where "userId" should be replaced by a valid userId 
	 * without {} and "newssourceId" should be replaced by a valid newsId without {}.
	 * 
	 */

@GetMapping("/newssource/{userId}/{newssourceId}")
public ResponseEntity<?> updateNewsourcewithuserID(@PathVariable("userId") String uId, @PathVariable("neessourceId") Integer nId)
{
	
	try {
		NewsSource newssource=newssserv.getNewsSourceById(uId, nId);	
		if (newssource==null) {
			return new ResponseEntity<String>("News Not found",HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<NewsSource>(newssource,HttpStatus.OK);
	} catch (NewsSourceNotFoundException e) {
		// TODO Auto-generated catch block
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
	}

}
	
	/*
	 * Define a handler method which will show details of all newssource created by specific 
	 * user. This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If the newssource found successfully. 
	 * 2. 404(NOT FOUND) - If the newssource with specified newsId is not found.
	 * This handler method should map to the URL "/api/v1/newssource/{userId}" using HTTP GET method
	 * where "userId" should be replaced by a valid userId without {}.
	 * 
	 */
@GetMapping("/newssource/{userId}")
public ResponseEntity<?> updateNewsource(@PathVariable("userId") String uId)
{
	
	
		List<NewsSource> lnews=newssserv.getAllNewsSourceByUserId(uId);
		if(lnews==null) {
			return new ResponseEntity<String>("No news found",HttpStatus.NOT_FOUND);
			
		}	
			
		
		return new ResponseEntity<List<NewsSource>>(lnews,HttpStatus.OK);
	

}
    
}
