package com.wc.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wc.pojo.Topic;

@Service("topicService")
public class TopicService {

	
	
	private List<Topic> topicList	=	new ArrayList<Topic>(Arrays.asList(
			new Topic("Spring","SpringFrameWork","Spring Framework Description"),
			new Topic("Hibernate","HibernateFrameWork","Hibernate Framework Description"),
			new Topic("Adobe","Adobe","Adobe Description")
			));
			
	public List<Topic> getAllTopics(){
		return topicList;
	}
	
	public Topic getTopicById(String id){
		try{
			return topicList.stream().filter(t ->t.getId().equals(id)).findFirst().get();	
		}catch(Exception e){
			return new Topic();
		}
		
	}
	
	public Topic getTopicById1(String id){
		try{
			Topic t1	=	null;
			for(Topic t: topicList){
				if(t.getId().equals(id)){
					t1	=	 t;
					break;
				}
			}
			return t1;
			//return topicList.stream().filter(t ->t.getId().equals(id)).findFirst().get();	
		}catch(Exception e){
			return new Topic();
		}
		
	}
	
	
	public void addTopic(Topic topic){
		topicList.add(topic);
	}
}
