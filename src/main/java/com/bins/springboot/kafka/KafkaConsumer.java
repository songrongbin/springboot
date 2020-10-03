package com.bins.springboot.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
	
	@KafkaListener(topics = {"${log.df-kafkaconsumer.topic}"}, autoStartup = "${listen.auto.start:false}")
    public void listen (ConsumerRecord<?, ?> record, Acknowledgment acknowledgment, Consumer<?, ?> consumer){
		try {
			String logStr = (String) record.value();
			System.out.println("kafka消息消费" + logStr);
			acknowledgment.acknowledge();
		} catch (Exception e) {
			e.printStackTrace();
			// logger.error("kafka消息消费失败", e);
		}
    }

}
