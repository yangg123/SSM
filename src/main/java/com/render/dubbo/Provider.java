package com.render.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yg on 2017/5/8.
 */
public class Provider {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"dubbo-provider.xml"});
        context.start();
        System.in.read(); // 为保证服务一直开着，利用输入流的阻塞来模拟
    }
}
