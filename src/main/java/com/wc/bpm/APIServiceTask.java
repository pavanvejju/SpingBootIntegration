/*package com.wc.bpm;

import java.lang.reflect.Method;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.wc.utils.JsonUtil;

@Component("apiServiceTask")
public class APIServiceTask implements JavaDelegate{
	
	private Expression service;
	private Expression method;
	private Expression dto;
	private Expression fields;
	private Expression returnVal;
	
	@Autowired
	private ApplicationContext context;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(APIServiceTask.class);
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		Object response	=	null;
		String responseFields = null;
		
		String serviceName = (String) service.getValue(execution);
		String methodName = (String) method.getValue(execution);
		String dtoName = (String) dto.getValue(execution);
		String fieldJson = (String) fields.getValue(execution);
		if (returnVal != null && returnVal.getValue(execution) != null) {
			responseFields = (String) returnVal.getValue(execution);
		}
		
		LOGGER.info("service Name ..'" +serviceName + "'.MethodName....."+methodName);	
		service = null;
		method = null;
		dto = null;
		fields = null;
		returnVal = null;
		
		try {
			Class dtoClass = Class.forName(dtoName);
			Object dtoObject = dtoClass.newInstance();
			dtoObject = JsonUtil.convertToObject(fieldJson, dtoClass);

			Object object = context.getBean(serviceName);
			Class serviceClass = object.getClass();
			Method method = serviceClass.getDeclaredMethod(methodName, dtoClass);
			response = method.invoke(object, dtoObject);
			LOGGER.info("Service Response::::::::::" + response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("API call failed for " + serviceName + "." + methodName + ".\n" + e.getMessage());
		}
	}
}*/