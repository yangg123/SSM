package com.ssm.service;

import com.ssm.pojo.Page;
import com.ssm.model.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IUserService {

	public void sayHello(String hello);
	public void createUser(User user);
	int getAllUsersCount();
	List<User> getAllUsersList(Page page);
	void addUsers(User user);

	User getUserById(int userId);
	PageInfo<User> queryByPage(String userName,Integer pageNo,Integer pageSize);

}
