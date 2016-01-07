package com.changxx.srpc.test;

import com.changxx.srpc.conf.ConsumerConfig;

/**
 * @author changxx on 2016/1/6.
 */
public class ClientMainTest {

    public static void main(String[] args) {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setInterfaceId("com.changxx.srpc.test.HelloInterface");
        consumerConfig.setAlias("changxx");

        HelloInterface consumer = (HelloInterface) consumerConfig.refer();

        System.out.println("结果：" + consumer.echo("changxx"));
    }

}
