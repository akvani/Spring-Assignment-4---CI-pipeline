package com.stackroute.newz.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newz.model.News;
import com.stackroute.newz.model.NewsSource;
import com.stackroute.newz.model.UserNews;
import com.stackroute.newz.repository.NewsRepository;
import com.stackroute.newz.util.exception.NewsNotFoundException;

/*
* Service classes are used here to implement additional business logic/validation 
* This class has to be annotated with @Service annotation.
* @Service - It is a specialization of the component annotation. It doesn't currently 
* provide any additional behavior over the @Component annotation, but it's a good idea 
* to use @Service over @Component in service-layer classes because it specifies intent 
* better. Additionally, tool support and additional behavior might rely on it in the 
* future.
* */

@Service
public class NewsServiceImpl implements NewsService {

	NewsRepository newsrepo;
	@Autowired
    public NewsServiceImpl(NewsRepository newsrepo) {
   	 this.newsrepo=newsrepo;
    }

	/*
	 * Autowiring should be implemented for the NewsDao and MongoOperation.
	 * (Use Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */
	

	/*
	 * This method should be used to save a new news.
	 */
	@Override
	public boolean addNews(News news) {
		boolean result=false;
		try
		{
		 Optional<UserNews> userexist= newsrepo.findById(news.getNewssource().getNewsSourceCreatedBy());
		 if(userexist.isPresent()) {
			// System.out.println("user exists");
		     UserNews usernewsexist = userexist.get();
		     List<News> newsexist=usernewsexist.getNewslist();
			   Iterator<News> iterator = newsexist.iterator();
				while(iterator.hasNext())
				 {
					 News data = iterator.next();
					 if(news.getNewsId() == data.getNewsId()) {
						iterator.remove();
						result=false;
					 }
					 else
					 {
						 result=true;
					 }
				    
				 }
				if(result)
				{
			         newsexist.add(news);
			         usernewsexist.setNewslist(newsexist);
			         newsrepo.save(usernewsexist);
				}
				
		}
		else
		{		
			List<News> newslist = new ArrayList<>();
			newslist.add(news);
			UserNews usernews=new UserNews(news.getNewssource().getNewsSourceCreatedBy(),newslist);
					
			newsrepo.save(usernews);
			if(newsrepo.insert(usernews)!=null) {
			result=true;
			}
			//result=false;
		}
		}
		catch(Exception e) {
			result=false;
		}
		return result;
		
	}
	/*
	
	public boolean addNews(News news) {
		
		Optional<UserNews> userexist= newsrepo.findById(news.getNewsSource().getNewssourceCreatedBy());
		
		if (userexist.isPresent()){
			
			News newnews=news;
			
			UserNews usernews=userexist.get();
			List <News> existnews=usernews.getNewslist();
			existnews.add(newnews);		
			
		}
		
		
		//UserNews unews=news.setNewsSource(news.getNewsSource());
		
		
	//	newsrepo.save(news);
		return true;
	} * 
	 */

	/* This method should be used to delete an existing news. */
	
	public boolean deleteNews(String userId, int newsId)  {
		try {
			Optional<UserNews> userexist= newsrepo.findById(userId);
			if (userexist.isPresent()){
				UserNews euser=userexist.get();
				List<News> newslist=euser.getNewslist();
				Iterator<News> iterator=newslist.iterator();
				
				while(iterator.hasNext())
				{
					News item=iterator.next();
					
					if (item.getNewsId().equals(newsId))
					
						iterator.remove();
							
				}
					euser.setNewslist(newslist);
					newsrepo.save(euser);
					return true;
			}
			
		}catch(NoSuchElementException ne) {
			throw new NoSuchElementException("News not found");
		}
		
		return false;
		//throw new NewsNotFoundException("News not found");
	}

	/* This method should be used to delete all news for a  specific userId. */
	
	public boolean deleteAllNews(String userId) throws NewsNotFoundException {
		try {
			Optional<UserNews> userexist= newsrepo.findById(userId);
			if (userexist.isPresent()){
				UserNews euser=userexist.get();
				List<News> newslist=euser.getNewslist();
				newslist.removeAll(newslist);
				return true;
				
			}else
			{
				throw new NewsNotFoundException("News not found in DeleteAll News");
			}
			
		}catch(NoSuchElementException ne) {
			throw new NewsNotFoundException("News not found");
		}
		
		//return true;
	}

	/*
	 * This method should be used to update a existing news.
	 */

	public News updateNews(News news, int newsId, String userId) throws NewsNotFoundException {
		
		try {
			Optional<UserNews> userexist= newsrepo.findById(userId);
			if (userexist.isPresent()){
				UserNews euser=userexist.get();
				List<News> newslist=euser.getNewslist();
				Iterator<News> iterator=newslist.iterator();
				
				while(iterator.hasNext())
				{
					News item=iterator.next();
					
					if (item.getNewsId().equals(newsId))
					{
						//title,author,description,publishedAt,content,url,urlToImage,Reminder,
						 // NewsSource
						news.setTitle(news.getTitle());
					news.setAuthor(news.getAuthor());
					news.setDescription(news.getDescription());
					news.setContent(news.getContent());
					news.setUrl(news.getUrl());
					news.setUrlToImage(news.getUrlToImage());
					news.setReminder(news.getReminder());
					news.setNewssource(news.getNewssource());	
					}
							
				}
					euser.setNewslist(newslist);
					newsrepo.save(euser);				
				
			}
			else {
			throw new NewsNotFoundException("News not found");
			}
			
		}catch(NoSuchElementException ne) {
			throw new NewsNotFoundException("News not found");
		}
		
		return news;
	}

	/*
	 * This method should be used to get a news by newsId created by specific user
	 */

	public News getNewsByNewsId(String userId, int newsId) throws NewsNotFoundException {
		try {
			Optional<UserNews> userexist= newsrepo.findById(userId);
			if (userexist.isPresent()){
				UserNews euser=userexist.get();
				List<News> newslist=euser.getNewslist();
				Iterator<News> iterator=newslist.iterator();
				News news=null;
				while(iterator.hasNext())
				{
					News item=iterator.next();
					
					if (item.getNewsId().equals(newsId))
					{
						news=item;
						//return item;
					}
				}
				return news;
				//throw new NewsNotFoundException("News not found");
			}
			else {
				throw new NewsNotFoundException("News not found");}
			
		}catch(NoSuchElementException ne) {
			throw new NewsNotFoundException("News not found");
		}
		
		
	}

	/*
	 * This method should be used to get all news for a specific userId.
	 */

	public List<News> getAllNewsByUserId(String userId) {
		try {
			Optional<UserNews> userexist= newsrepo.findById(userId);
			if (userexist.isPresent()){
				UserNews euser=userexist.get();
				List<News> newslist=euser.getNewslist();
				return newslist;
			}
			
		}catch(NoSuchElementException ne) {
			throw new NoSuchElementException("News not found");
		}
		
		
		return null;
	}

}
