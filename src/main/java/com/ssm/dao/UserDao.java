package com.ssm.dao;

import com.ssm.pojo.Page;
import com.ssm.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectUserByUserName(@Param("userName") String userName);


    int getAllUsersCount();
    List<User> getAllUsersList(Page page);
    void addUser(User user);
}