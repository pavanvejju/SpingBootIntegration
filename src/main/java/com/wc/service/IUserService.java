package com.wc.service;

import java.util.List;

import com.wc.dto.LoginDTO;
import com.wc.dto.UserDTO;
import com.wc.pojo.User;

public interface IUserService {

	public List<User> fetchUsers();

	public void addUser(User user);

	public User validateLogin(LoginDTO loginDto);

	public User updateUserInfo(Long id, UserDTO userDTO);

	public User testFetchThread(Long id);
}
