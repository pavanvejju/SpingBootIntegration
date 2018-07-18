package com.wc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wc.dao.impl.UserDAO;
import com.wc.dto.LoginDTO;
import com.wc.dto.UserDTO;
import com.wc.pojo.User;
import com.wc.service.IUserService;

@Service("userService")
public class UserService implements IUserService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	@Transactional
	public List<User> fetchUsers(){
		return userDAO.fetchUsers();
	}
	
	@Override
	@Transactional
	public void addUser(User user){
		userDAO.saveUser(user);
	}
	
	@Override
	@Transactional
	public User validateLogin(LoginDTO loginDto){
		
		try{
			User userObj	=	null;
			List<User> userList	= userDAO.fetchUsers();
			for(User user1: userList){
				if(user1.getUserName().equals(loginDto.getUserName()) && user1.getPassword().equals(loginDto.getPassword())){
					userObj	=	new User();
					userObj	=	user1;
					break;
				}
			}
			return userObj;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	@Transactional
	public User updateUserInfo(Long id, UserDTO userDTO) {
		User userFromDb	= userDAO.findById(id, true);
			userFromDb.setFirstName(userDTO.getUserName());
			userFromDb.setLastName(userDTO.getUserName());

			User user	=	userDAO.merge(userFromDb);
		return user;
	}
	
	@Transactional
	public User fetchUserById(Long id) {
		
		User userFromDb	= userDAO.findById(id, true);
		
		
		return userFromDb;
	}
	
	@Transactional(isolation	=	 Isolation.READ_UNCOMMITTED)
	@Override
	public User testFetchThread(Long id) {
		
		UserDTO userDTO	=	new UserDTO();
			userDTO.setFirstName("One");
			userDTO.setLastName("Test");
		updateUserInfo(id,userDTO);
		
		User u	=	fetchUserById(id);
		return u;
		
	}
	
	
}
