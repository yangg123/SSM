package com.render.zbus;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.zbus.broker.Broker;
import org.zbus.broker.BrokerConfig;
import org.zbus.broker.SingleBroker;
import org.zbus.mq.Consumer;
import org.zbus.mq.MqConfig;
import org.zbus.net.core.Session;
import org.zbus.net.http.Message;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by yg on 2017/3/16.
 */
public class ComsumerServer {

    public static void main(String[] args) throws Exception {

        //创建Broker代表
        BrokerConfig brokerConfig = new BrokerConfig();
        brokerConfig.setServerAddress("127.0.0.1:15555");
        Broker broker = new SingleBroker(brokerConfig);

        MqConfig config = new MqConfig();
        config.setBroker(broker);
        config.setMq("gangan");

        //创建消费者
        @SuppressWarnings("resource")
        Consumer c = new Consumer(config);
        c.onMessage(new Message.MessageHandler() {
            @Override
            public void handle(Message msg, Session sess) throws IOException {
                System.out.println("消费者收到的消息："+msg);
               // HashMap<String,Object> map= JSON.parseObject(msg.getBodyString(), new TypeReference<HashMap<String,Object>>(){});
                SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                System.out.println("处理完毕!"+df.format(new Date()));
            }
        });

        //启动消费线程
        c.start();
    }
}
