package com.refarch.twitter.twitterstream;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

/*
 * Application URL to start twitter poling for Jio
 * http://127.0.0.1:8080/kafka/twetterFeed?keyWord="Jio"
 */

@SpringBootApplication
public class TwitterStramApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterStramApplication.class, args);
	}
	
	/*@Bean
	public  void search() throws TwitterException {
		Twitter twitter = TwitterFactory.getSingleton();
		Query query = new Query("Jio");
		QueryResult result = twitter.search(query);
		for (Status status : result.getTweets()) {
	        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
	    }
		
	}*/
}
