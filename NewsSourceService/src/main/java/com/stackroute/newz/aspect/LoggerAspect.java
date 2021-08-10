package com.stackroute.newz.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.stackroute.newz.model.NewsSource;



/* Annotate this class with @Aspect and @Component */
@Aspect
@Component
public class LoggerAspect {
	
	
Logger newslogger=LoggerFactory.getLogger(LoggerAspect.class);
	
	@Before("execution (* com.stackroute.newz.controller.NewsSourceController.addnews(..))")
	public void showdata(JoinPoint  jp)
	{
		newslogger.info("News Added to the list");
	}
	
	
	@Before("updatenewshandler()")
	public void updatenews(JoinPoint jp)
	{
		newslogger.info("News will be updated");
	}
	@After("updatenewshandler()")
	public void updatenewsA(JoinPoint jp)
	{
		newslogger.info("News found and News is updated");
	}
	
	@Around("updatenewshandler()")
	public Object aroundsaveuniverse(ProceedingJoinPoint proceedobj) throws Throwable
	{
		
       ResponseEntity<?> obj=(ResponseEntity<?>) proceedobj.proceed();		
     
     	ResponseEntity<?> resentity=obj;
     	try
     	{
    		NewsSource news=(NewsSource)resentity.getBody();
    		newslogger.info("client updated news named " + news.getNewsSourceName() + " of id " + news.getNewsSourceId() );
     		
     	}
		catch(Exception e)
     	{
			newslogger.warn("Updated call is not success whil adding university. Excepiton raised" + e.getMessage());
     	}
     	
     	return obj;
		
	}
	
	@AfterThrowing(pointcut="updatenewshandler()",throwing="exceptobj")
	public void updateexceptionhandler(Exception exceptobj)
	{
		newslogger.warn("Exception raised while update" + exceptobj);
	}
	
	
	@Pointcut("execution (* com.stackroute.newz.controller.NewsSourceController.updateNewssource(..))")
	public void updatenewshandler()
	{
		
	}

	/*
	 * Write loggers for each of the methods of NewsController, any particular method
	 * will have all the four aspectJ annotation
	 * (@Before, @After, @AfterReturning, @AfterThrowing).
	 */


}
