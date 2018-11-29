# Kafka_Twitter
Receive Stream from Twitter and send to Kafka. Consumer of Kafka will take stream from Topic and store data in Flat file
twitter4j has been used for taking feed from twitter.
twitter-stram - This project is a spring boot application used for straming data from Twitter.
Tweeter access tokens need to be genarated first. This application exposed a rest service where the filetr string for twitter feed cam be mentioned.
For example: http://localhost:8080/kafka/twetterFeed?keyWord=Jio
Using this URL "Jio" releted all the post in twitter will be received ans sent to Kafka using Spring-Kafka
