package com.stackroute.newz.service;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.stackroute.newz.model.NewsSource;
import com.stackroute.newz.repository.NewsSourceRepository;
import com.stackroute.newz.util.exception.NewsSourceNotFoundException;

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
public class NewsSourceServiceImpl implements NewsSourceService {
	
	NewsSourceRepository newssrepo;
	
	@Autowired
	public NewsSourceServiceImpl(NewsSourceRepository newssrepo) {
		this.newssrepo=newssrepo;
	}

	/*
	 * Autowiring should be implemented for the NewsDao and MongoOperation.
	 * (Use Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */

	/*
	 * This method should be used to save a newsSource.
	 */

	@Override
	public boolean addNewsSource(NewsSource newsSource) {
		try {
			Optional<NewsSource> nsource=newssrepo.findById(newsSource.getNewsSourceId());
			 if (nsource.isPresent())
			 {
				 return false;
			 }else {
				 NewsSource Nnewsource=newsSource;
				 newssrepo.save(Nnewsource);
				 if(newssrepo.insert(Nnewsource)!=null) {
					 return true;
				 }else
				 {
					 return false;
				 }
				
			 }
		}
	catch(NoSuchElementException ne) {
		throw new NoSuchElementException("News not found");
	}
		
		
		
		
	}

	/* This method should be used to delete an existing newsSource. */

	@Override
	public boolean deleteNewsSource(int newsSourceId) {
		try {
			Optional<NewsSource> optnews=newssrepo.findById(newsSourceId);
			if (optnews==null) {
				return false;
				
			}else {
			if (optnews.isPresent()) {
				NewsSource newsource=optnews.get();
				newssrepo.delete(newsource);
				return true;
			}
			}		
			
			
		}catch(NoSuchElementException ne) {
			throw new NoSuchElementException("News not found");
		}
		
		return false;
	}

	/* This method should be used to update an existing newsSource. */
	
	@Override
	public NewsSource updateNewsSource(NewsSource newsSource, int newsSourceId) throws NewsSourceNotFoundException {
		
		try {
			
			Optional<NewsSource> optnews=newssrepo.findById(newsSourceId);
			if (optnews.isPresent()) {
				NewsSource newsource=optnews.get();
				newsource.setNewsSourceName(newsource.getNewsSourceName());
				newsource.setNewsSourceCreatedBy(newsource.getNewsSourceCreatedBy());
				newsource.setNewsSourceDesc(newsource.getNewsSourceDesc());
				return newsource;
			}else
			{
				throw new NewsSourceNotFoundException("News Soruce not found to update");
			}
			
		}catch(NoSuchElementException ne) {
			throw new NewsSourceNotFoundException("News not found");
		}
		
		
		
	}

	/* This method should be used to get a specific newsSource for an user. */

	@Override
	public NewsSource getNewsSourceById(String userId, int newsSourceId) throws NewsSourceNotFoundException {
		try {
			int count=0;
			NewsSource nsource=new NewsSource();
			List<NewsSource> newslist=newssrepo.findAllNewsSourceByNewsSourceCreatedBy(userId);
			
			Iterator<NewsSource> iterator = newslist.iterator();
			while(iterator.hasNext())
			 {
				NewsSource data = iterator.next();
				 if(newsSourceId == data.getNewsSourceId()) {
					count++;
					nsource=data;
				 }			
			    
			 }
			if(count==0) {
				throw new NewsSourceNotFoundException("News not found");
			}else {
				return nsource;
			}
			
			
		}catch(NoSuchElementException e) {
			//throw new NewsSourceNotFoundException("News not found");
			//throw new NoSuchElementException();
			return null;
		}
		
//		NewsSource result=null;
//		try {
//			if (userId==null) {
//				Optional<NewsSource> optnews=newssrepo.findById(newsSourceId);
//				if (optnews.isPresent()) {
//					NewsSource newssource=optnews.get();
//					return newssource;
//				}else
//				{
//					throw new NewsSourceNotFoundException("News not found");
//				}
//				
//				
//			}
//		
//			
//		
//			
//			Optional<NewsSource> optnews=newssrepo.findById(newsSourceId);
//			if (optnews.isPresent()) {
//				NewsSource newssource=optnews.get();
//				return newssource;
//			}else
//			{
//				throw new NewsSourceNotFoundException("News not found");
//			}
//			
//			
//		}catch(NoSuchElementException ne) {
//			throw new NewsSourceNotFoundException("News not found");
//		}
//		
	}

	
	 /* This method should be used to get all newsSource for a specific userId.*/

	@Override
	public List<NewsSource> getAllNewsSourceByUserId(String createdBy) {	
		List<NewsSource> optnews=newssrepo.findAllNewsSourceByNewsSourceCreatedBy(createdBy);
		
		if (optnews==null) {
			return null;
			
			
		}else
		{
			return optnews;
		
		}
		
	}

}
