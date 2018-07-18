package com.wc.dao;

import java.util.List;

import com.wc.pojo.User;


public interface IUserDAO {

	public List<User> fetchUsers();

	public void saveUser(User user);
}
