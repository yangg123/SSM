package com.ssm.service.impl;

import javax.annotation.Resource;

import com.ssm.pojo.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import com.ssm.dao.UserDao;
import com.ssm.model.User;
import com.ssm.service.IUserService;

import java.util.List;

@Service("userService")
public class UserServiceImpl  implements IUserService{
	
	@Resource
	private UserDao userDao;


	@Override
	public void sayHello(String hello) {
		System.out.print("say hello.....");
	}

	@Override
	public void createUser(User user) {
		System.out.println("save user.");
	}

	@Override
	public int getAllUsersCount() {
		return userDao.getAllUsersCount();
	}

	@Override
	public List<User> getAllUsersList(Page page) {
		return userDao.getAllUsersList(page);
	}

	@Override
	public void addUsers(User user) {
		userDao.addUser(user);
	}

	//=========================  用PageController 插件 =====================//

	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return this.userDao.selectByPrimaryKey(userId);
	}

	@Override
	public PageInfo<User> queryByPage(String userName, Integer pageNo, Integer pageSize) {

		System.out.print("\n---- userName is:" + userName);
		pageNo = pageNo == null?1:pageNo;
		pageSize = pageSize == null?10:pageSize;
		PageHelper.startPage(pageNo, pageSize);


		List<User> list = this.userDao.selectUserByUserName(userName);
		System.out.print("\n--- list是" + list.size() + "\n--");


		//用PageInfo对结果进行包装
		PageInfo<User> page = new PageInfo<User>(list);
		//测试PageInfo全部属性
		System.out.println(page.getPageNum());
		System.out.println(page.getPageSize());
		System.out.println(page.getStartRow());
		System.out.println(page.getEndRow());
		System.out.println(page.getTotal());
		System.out.println(page.getPages());
		System.out.println(page.getFirstPage());
		System.out.println(page.getLastPage());
		System.out.println(page.isHasPreviousPage());
		System.out.println(page.isHasNextPage());

		for (User user : page.getList()) {
//			System.out.print("\n--- 名字是" + user.getUserName());
		}
		return page;
	}
}
