package com.ssm.pojo;

/**
 * Created by codingBoy on 16/11/28.
 */
//将所有的ajax请求返回类型，全部封装成json数据
public class Result<T> {

    //请求是否成功
    private boolean success;
    private T data;
    private String error;

    public Result(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public Result(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public Result(boolean success, String error, T data) {
        this.success = success;
        this.error = error;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
