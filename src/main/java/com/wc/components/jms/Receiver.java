package com.wc.components.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
	

	 
	@JmsListener(destination="wcqueue")
	public void reciever(String message){
		LOGGER.info("recieved message='{}'",message);
		//latch.countDown();
	}
}
