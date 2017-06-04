package com.ssm.dao;

import com.ssm.pojo.Bigclass;
import com.ssm.pojo.Smallclass;

/**
 * 行业 数据持久化 接口
 * Created by yg on 2017/5/2.
 */
public interface JobDao {
    public int saveBig(Bigclass bigclass);
    public int saveSmall(Smallclass smallclass);
}
