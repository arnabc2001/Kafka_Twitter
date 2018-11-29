package com.refarch.twitter.twitterstream.controller;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.refarch.twitter.twitterstream.model.TweetFeed;
import com.refarch.twitter.twitterstream.service.KafkaSender;

import twitter4j.FilterQuery;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TweetEntity;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

@RestController
@RequestMapping(value = "/kafka/")
public class ApacheKafkaWebController {

	@Autowired
	KafkaSender kafkaSender;
	
	TweetFeed feedDetails = null;
	
	ObjectWriter ow = new ObjectMapper().writer();

	/*
	 * @GetMapping(value = "/producer") public String
	 * producer(@RequestParam("message") String message) {
	 * kafkaSender.send(message);
	 * 
	 * return "Message sent to the Kafka Topic twitterFeed Successfully"; }
	 */

	@GetMapping(value = "/twetterFeed")
	public void producer(@RequestParam("keyWord") String word) {

		BlockingQueue<Status> lBlockingQueue = new LinkedBlockingQueue<Status>(1000);

		TwitterStream tStream = new TwitterStreamFactory().getInstance();
		tStream.addListener(createListener(lBlockingQueue));
		/*
		 * FilterQuery query = new FilterQuery().track(word);
		 * tStream.filter(query);
		 */

		tStream.filter(word);

		while (true) {
			if (!lBlockingQueue.isEmpty()) {
				feedDetails = new TweetFeed();
				try {
					Status status = lBlockingQueue.take();
					feedDetails.setUserId(String.valueOf(status.getUser().getId()));
					feedDetails.setUserName(status.getUser().getName());
					feedDetails.setPostDate(status.getCreatedAt());
					feedDetails.setMessageTxt(status.getText());
					feedDetails.setUserLocation(status.getUser().getLocation());
					feedDetails.setTimeZone(status.getUser().getTimeZone());
					/*feedDetails.getGeoLocation().setLatitude(status.getGeoLocation().getLatitude());
					feedDetails.getGeoLocation().setLongitude(status.getGeoLocation().getLongitude());*/
					
					String message = ow.writeValueAsString(feedDetails);
					//System.out.println(message);
					
					
					kafkaSender.send(message);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		
	}

	private StatusListener createListener(BlockingQueue<Status> queue) {
		return new StatusListener() {

			@Override
			public void onException(Exception ex) {
				// TODO Auto-generated method stub
				ex.printStackTrace();

			}

			@Override
			public void onTrackLimitationNotice(int arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStatus(Status arg0) {
				// TODO Auto-generated method stub
				
				queue.offer(arg0);

			}

			
			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrubGeo(long arg0, long arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice arg0) {
				// TODO Auto-generated method stub

			}
		};

	}

}
