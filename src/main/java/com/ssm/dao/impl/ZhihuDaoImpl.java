package com.ssm.dao.impl;

import com.ssm.dao.ZhihuDao;
import com.ssm.pojo.ZhihuUser;
import com.render.kit.DBHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * 知乎 数据库持久化接口 实现
 * @author 甘海彬
 *
 */
public class ZhihuDaoImpl implements ZhihuDao {
    @Override
    public int saveUser(ZhihuUser user) {
        DBHelper dbhelper = new DBHelper();
        StringBuffer sql = new StringBuffer();
        sql.append("INSERT INTO spider_zhihu_user ( `key`,`name`,identity,location,profession,sex,school,major,recommend,picUrl,agree,thanks,ask,answer,article,collection)")
                .append("VALUES (? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? ) ");
        //设置 sql values 的值
        List<String> sqlValues = new ArrayList<>();
        sqlValues.add(user.getKey());
        sqlValues.add(user.getName());
        sqlValues.add(user.getIdentity());
        sqlValues.add(user.getLocation());
        sqlValues.add(user.getProfession());
        sqlValues.add(""+user.getSex());
        sqlValues.add(user.getSchool());
        sqlValues.add(user.getMajor());
        sqlValues.add(user.getRecommend());
        sqlValues.add(user.getPicUrl());
        sqlValues.add(""+user.getAgree());
        sqlValues.add(""+user.getThanks());
        sqlValues.add(""+user.getAsk());
        sqlValues.add(""+user.getAnswer());
        sqlValues.add(""+user.getArticle());
        sqlValues.add(""+user.getCollection());
        int result = dbhelper.executeUpdate(sql.toString(), sqlValues);
        return result;
    }
}