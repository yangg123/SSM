package com.render.zbus;

import com.render.dubbo.DemoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/8/23.
 */
public class ZbusRpcClient {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("zbus-client.xml");

        DemoService intf = (DemoService) context.getBean("demoService");
        System.out.println(intf.sayHello("我的名字gangan"));
    }
}

//客户端-消费者
//服务端-生成者-提供者