package com.render.zbus;

import org.zbus.broker.Broker;
import org.zbus.broker.BrokerConfig;
import org.zbus.broker.SingleBroker;
import org.zbus.mq.Producer;
import org.zbus.net.http.Message;

/**
 * Created by yg on 2017/3/16.
 */
public class ProducerClient {

    public static void main(String[] args) throws Exception {
        //创建Broker代理
        BrokerConfig config = new BrokerConfig();
        config.setBrokerAddress("101.132.77.170:15555");
        final Broker broker = new SingleBroker(config);

        Producer producer = new Producer(broker, "gangan");
        producer.createMQ(); // 如果已经确定存在，不需要创建

        //创建消息，消息体可以是任意binary，应用协议交给使用者
        Message msg = new Message();
        msg.setBody("这个一个来自生产者的消息");
        producer.sendSync(msg);
        broker.close();
    }
}
