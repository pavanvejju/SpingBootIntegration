package com.wc.components.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

	public void send(String destination,String message){
		
		LOGGER.info("Sending message='{}' to destination='{}'",message,destination);
		jmsTemplate.convertAndSend(destination, message);	
	}
	
}
