package com.ssm.pojo;

/**
 * Created by yg on 2017/5/2.
 */
public class Smallclass {

    private Long id;
    private String smallName;
    private Long bigId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSmallName() {
        return smallName;
    }

    public void setSmallName(String smallName) {
        this.smallName = smallName;
    }

    public Long getBigId() {
        return bigId;
    }

    public void setBigId(Long bigId) {
        this.bigId = bigId;
    }

    @Override
    public String toString() {
        return "Smallclass{" +
                "id=" + id +
                ", smallName='" + smallName + '\'' +
                ", bigId=" + bigId +
                '}';
    }
}
