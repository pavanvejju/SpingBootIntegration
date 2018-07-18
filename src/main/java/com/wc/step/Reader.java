package com.wc.step;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import com.wc.pojo.User;
import com.wc.service.impl.UserService;

public class Reader  implements ItemReader<String>{

	private String[] messages = { "wc.com",
			"Welcome to Spring Batch Example",
			"We use MySql Database for this example" };
	
	
	@Autowired
	private UserService userService;
	
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		List<User> userList	=	userService.fetchUsers();
		System.out.println("userList:::"+userList);
		return null;
	}

}
