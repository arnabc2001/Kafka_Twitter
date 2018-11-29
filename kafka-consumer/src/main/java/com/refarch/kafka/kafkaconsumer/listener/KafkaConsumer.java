package com.refarch.kafka.kafkaconsumer.listener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
	
	
	@Autowired
	private File f;
	
	
	
	
	@KafkaListener(topics = "twitterFeed", groupId = "tweeter")
    public void consume(String message) {
		try {
			
			writeToFile(message,f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
        //System.out.println("Consumed message: **************** " + message);
        
    }

	private void writeToFile(String message, File f) throws IOException {
		
		FileWriter fw = new FileWriter(f,true);
		BufferedWriter br =new BufferedWriter(fw);
		br.write(message);
		br.write("\n");
		//fw.append();
		//fw.flush();
		br.close();
		fw.close();
		
		// TODO Auto-generated method stub
		
	}

}
