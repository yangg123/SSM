package com.render.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 过滤器学习
 * Created by yg on 2017/6/1.
 */
public class FirstFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("执行第一个过滤器init");
        String value = filterConfig.getInitParameter("name");
        System.out.println("过滤器的参数值:"+value);
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("start filter..");
        // filter 只是链式处理，请求依然转发到目的地址。
        filterChain.doFilter(arg0,arg1);
        System.out.println("end filter..");
    }

    @Override
    public void destroy() {
        System.out.println("destroy..");
    }
}
