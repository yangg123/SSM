package com.render.zbus;

import com.render.dubbo.DemoService;
import org.zbus.broker.Broker;
import org.zbus.broker.BrokerConfig;
import org.zbus.broker.SingleBroker;
import org.zbus.rpc.RpcFactory;
import org.zbus.rpc.mq.MqInvoker;

/**
 * Created by Administrator on 2017/8/23.
 */
public class ZbusRpcServer {

    public static void main(String[] args) throws Exception {
        BrokerConfig config = new BrokerConfig();
        config.setBrokerAddress("101.132.77.170:15555");
        Broker broker = new SingleBroker(config);

        //2)创建基于MQ的Invoker以及Rpc工厂，指定RPC采用的MQ为MyRpc
        MqInvoker invoker = new MqInvoker(broker, "myrpc");
        RpcFactory factory = new RpcFactory(invoker);

        //3) 动态代理出实现类
        DemoService hello = factory.getService(DemoService.class);
        broker.close();
    }
}
