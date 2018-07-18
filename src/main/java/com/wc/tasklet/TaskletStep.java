/*package com.wc.tasklet;

import java.io.File;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class TaskletStep implements Tasklet{

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		try{
			File file	=	new File("myFile.txt");
			if(file.delete()){
				System.out.println("###Tasklet step :: file deleted"+file.getName());
			}else{
				System.out.println("delete operation failed");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return RepeatStatus.FINISHED;
	}

}
*/