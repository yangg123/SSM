package com.ssm.controler;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yg on 2017/3/21.
 */

public class HelloWorldCacheController extends AbstractController {
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        System.out.print("\n-----设置缓存的话，在缓存之内不会再次执行该方法");
        //点击后再次请求当前页面
        resp.getWriter().write("<a href=''>this</a>");
        return null;
    }
}