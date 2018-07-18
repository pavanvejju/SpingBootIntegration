/*package org.activiti;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiTestCase;
import org.activiti.engine.test.Deployment;
import org.junit.Test;

public class ActivitiTest1 extends ActivitiTestCase{
	
	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();
	
	
	@Test
	@Deployment(resources = {"org/activiti/test/my-process.bpmn20.xml"})
	public void testSimpleProcess() {
		
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("employeeName", "pavanvejju");
		variables.put("numberOfDays", new Integer(4));
		variables.put("vacationMotivation", "I'm really tired!");
		runtimeService.startProcessInstanceByKey("vacationRequest", variables);
		// Fetch all tasks for the management group
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
		for (Task task : tasks) {
		 System.out.println("Task available: " + task.getName());
		}
	
		Task task = tasks.get(0);
		Map<String, Object> taskVariables = new HashMap<String, Object>();
		taskVariables.put("vacationApproved", "false");
		taskVariables.put("managerMotivation", "We have a tight deadline!");
		taskVariables.put("resendRequest", "false");
		taskService.complete(task.getId(), taskVariables);
	}
	
	
	@Test
	@Deployment(resources = {"org/activiti/test/my-process.bpmn20.xml"})
	public void test() {
		
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

		RepositoryService repositoryService = processEngine.getRepositoryService();
		repositoryService.createDeployment().addClasspathResource("org/activiti/test/VacationRequest.bpmn20.xml").deploy();
		System.out.println("Number of process definitions: " + repositoryService.createProcessDefinitionQuery().count());
		
		RuntimeService runtimeService = processEngine.getRuntimeService();
			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("employeeName", "Kermit");
			variables.put("numberOfDays", new Integer(4));
			variables.put("vacationMotivation", "I'm really tired!");

			ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("vacationRequest", variables);

		// Fetch all tasks for the management group
		TaskService taskService = processEngine.getTaskService();
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
		for (Task task : tasks) {
		 System.out.println("Task available: " + task.getName());
		}
	
		Task task = tasks.get(0);
		
		Map<String, Object> taskVariables = new HashMap<String, Object>();
		taskVariables.put("vacationApproved", "false");
		taskVariables.put("managerMotivation", "We have a tight deadline!");
		taskService.complete(task.getId(), taskVariables);
	
	}

}
*/