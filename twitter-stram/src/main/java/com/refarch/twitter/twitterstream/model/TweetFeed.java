package com.refarch.twitter.twitterstream.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TweetFeed {
	
	private String userName;
	private String userId;
	private Date postDate;
	private String messageTxt;
	private String userLocation;
	private String timeZone;
	/*private GeoLocation geoLocation;*/
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public String getMessageTxt() {
		return messageTxt;
	}
	public void setMessageTxt(String messageTxt) {
		this.messageTxt = messageTxt;
	}
	public String getUserLocation() {
		return userLocation;
	}
	public void setUserLocation(String location) {
		this.userLocation = location;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	/*public GeoLocation getGeoLocation() {
		return geoLocation;
	}
	public void setGeoLocation(GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}*/
	

}
