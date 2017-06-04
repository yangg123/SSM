package com.render.filter;

/**
 * Created by yg on 2017/6/4.
 */
import javax.servlet.Filter;

import javax.servlet.FilterChain;

import javax.servlet.FilterConfig;

import javax.servlet.ServletContext;

import javax.servlet.ServletRequest;

import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;



public class LogFilter implements Filter {

    private FilterConfig config;
    // 实现初始化方法
    public void init(FilterConfig config) {

        this.config = config;
        System.out.println("执行第2个过滤器init");
    }

    public void destroy() {
        this.config = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response,

                         FilterChain chain) {
        // 获取ServletContext 对象，用于记录日志
        ServletContext context = this.config.getServletContext();
        long before = System.currentTimeMillis();
        System.out.println("开始过滤... ");
        // 将请求转换成HttpServletRequest 请求
        HttpServletRequest hrequest = (HttpServletRequest) request;
        context.log("Filter已经截获到用户的请求的地址: " + hrequest.getServletPath());

        try {
            // Filter 只是链式处理，请求依然转发到目的地址。
            chain.doFilter(request, response);
            System.out.println("过滤结束... ");
        } catch (Exception e) {
            e.printStackTrace();
        }

        long after = System.currentTimeMillis();
        context.log("过滤结束");
        context.log(" 请求被定位到" + ((HttpServletRequest) request).getRequestURI()
                + "所花的时间为: " + (after - before));
    }
}