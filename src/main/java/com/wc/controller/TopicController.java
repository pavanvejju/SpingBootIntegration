package com.wc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wc.pojo.Topic;
import com.wc.service.impl.TopicService;

@RestController
public class TopicController {

	@Autowired
	private TopicService topicService;
	
	@RequestMapping(method=RequestMethod.GET,value="/api/wc/topics")
	public List<Topic> getAllTopics(){
		return topicService.getAllTopics();
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/api/wc/topics/{id}")
	public Topic getTopicById(@PathVariable String id){
		return topicService.getTopicById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/api/wc/topics")
	public void addTopic(@RequestBody Topic topic){
		 topicService.addTopic(topic);
	}
	
}
