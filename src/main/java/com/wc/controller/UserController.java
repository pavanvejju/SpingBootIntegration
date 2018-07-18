package com.wc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wc.dto.LoginDTO;
import com.wc.pojo.User;
import com.wc.service.IUserService;

@RestController
public class UserController {

	@Autowired
	private IUserService userService;
	
	@RequestMapping(method=RequestMethod.GET,value="/api/wc/users")
	public List<User> getAllUsers(){
		return userService.fetchUsers();
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/api/wc/user")
	public void addUser(@RequestBody User user){
		 userService.addUser(user);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/api/wc/loginsubmit")
	public User loginSubmit(@RequestBody LoginDTO loginDto){
		return  userService.validateLogin(loginDto);
	}
	
	
}
