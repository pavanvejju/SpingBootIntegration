/**
 * 
 */
package com.wc.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

/**
 * @author pavankumarv
 *
 */
public class JobCompletionListener extends JobExecutionListenerSupport{
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("Before Job");

	}

	
	@Override
	public void afterJob(JobExecution jobExecution) {
		
		if(jobExecution.getStatus()==BatchStatus.COMPLETED){
			System.out.println("BATCH JOB COMPLETED SUCCESSFULLY");
			
		}
	}

}
