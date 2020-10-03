package com.bins.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bins.springboot.kafka.KafkaProducer;

@RestController
public class KafkaController {
	
	@Autowired
	private KafkaProducer kafkaProducer;

	
	@RequestMapping("/product")
    public String hello() {
		kafkaProducer.sendLog();
        return "";
	}

}
