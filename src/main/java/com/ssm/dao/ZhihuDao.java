package com.ssm.dao;

import com.ssm.pojo.ZhihuUser;

/**
 * 知乎 数据持久化 接口
 * Created by yg on 2017/4/28.
 */
public interface ZhihuDao {

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public int saveUser(ZhihuUser user);
}
