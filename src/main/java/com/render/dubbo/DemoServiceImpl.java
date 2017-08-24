package com.render.dubbo;

import com.ssm.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yg on 2017/5/8.
 */

public class DemoServiceImpl implements DemoService {

    public String sayHello(String name) {
        return "Hello " + name;
    }

    public List getUsers() {
        List list = new ArrayList();
        User u1 = new User();
        u1.setUserName("jack");
        u1.setAge(20);


        User u2 = new User();
        u2.setUserName("tom");
        u2.setAge(21);


        User u3 = new User();
        u3.setUserName("rose");
        u3.setAge(19);

        list.add(u1);
        list.add(u2);
        list.add(u3);
        return list;
    }
}
