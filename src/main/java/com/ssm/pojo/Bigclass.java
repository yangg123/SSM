package com.ssm.pojo;

/**
 * Created by yg on 2017/5/2.
 */
public class Bigclass {

    private Long id; // 订单号
    private String bigName; // 订单信息

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getBigName() {
        return bigName;
    }

    public void setBigName(String bigName) {
        this.bigName = bigName;
    }

    @Override
    public String toString() {
        return "Bigclass{" +
                "id=" + id +
                ", bigName='" + bigName + '\'' +
                '}';
    }
}
