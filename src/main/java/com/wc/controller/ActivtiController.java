/**
 * 
 *//*
package com.wc.controller;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

*//**
 * @author pavankumarv
 *
 *//*
//@RestController
public class ActivtiController {

	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	
	@RequestMapping(value="/api/wc/start-my-process",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public void startMyProcess(){
		
		
		 Map<String, Object> processMap = new HashMap<String, Object>();
	        processMap.put("id", 4);
	        processMap.put("name", "a");
	        processMap.put("description", "afsdf");
	        
	        ProcessInstance	procInst = approvalWorkflowService.startProcess("myProcess", pMap);
	        
	        runtimeService.startProcessInstanceByKey("myProcess", processMap);
	        Task task	=	taskService.createTaskQuery().processInstanceId(processInstanceId).includeProcessVariables().singleResult();
	        taskService.complete(task.getId(), processMap);
		
		System.err.println("we have now"+ runtimeService.createProcessInstanceQuery().count());
		
	}
}
*/