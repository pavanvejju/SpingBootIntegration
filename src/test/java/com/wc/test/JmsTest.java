package com.wc.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wc.components.jms.Sender;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JmsTest {

  @Autowired
  private Sender sender;

 /* @Autowired
  private Receiver receiver;*/

  @Test
  public void testReceive() throws Exception {

	  sender.send("wcqueue", "Hello Boot! This is test sample1");
  //  receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
   // assertThat(receiver.getLatch().getCount()).isEqualTo(0);
  }
}