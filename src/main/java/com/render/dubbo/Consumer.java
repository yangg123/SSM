package com.render.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by yg on 2017/5/8.
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "dubbo-consumer.xml" });
        context.start();

        DemoService demoService = (DemoService) context.getBean("demoService");
        String hello = demoService.sayHello("tom"); // ִ
        System.out.println("\n------- 本地消费者的名字是" + hello);

        //
        List list = demoService.getUsers();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        }
    }
}
