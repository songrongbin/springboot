package com.bins.springboot.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
// @EnableScheduling
public class KafkaProducer {
	
	// @Autowired
    private KafkaTemplate kafkaTemplate;
 
	// @Scheduled(cron = "00/1 * * * * ?")
    public void sendLog(){
    	String log = "nihao";
        System.err.println("向kafka中生产消息:"+log);
        kafkaTemplate.send("dflog", log);
    }


}
