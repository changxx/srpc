package com.changxx.srpc.proxy;

import com.changxx.srpc.conf.ConsumerConfig;
import com.changxx.srpc.conf.ProviderConfig;
import com.changxx.srpc.invocation.RpcInvocation;
import com.changxx.srpc.rpc.NettyProviderServer;
import com.changxx.srpc.rpc.ProviderServer;

import java.lang.reflect.Proxy;

/**
 * @author changxx on 2016/1/6.
 */
public class RpcProxy<T> implements Rpc<T> {

    public void export(ProviderConfig<T> providerConfig) throws Exception {
        ProviderServer providerServer = new NettyProviderServer();
        providerServer.start();
    }

    public T refer(ConsumerConfig<T> consumerConfig) throws Exception {
        Class<T> interfaceClass = consumerConfig.getInterfaceClass();
        System.out.println("get rpc service " + consumerConfig.getInterfaceClass().getName());
        RpcInvocation rpcInvocation = new RpcInvocation(consumerConfig.getConsumerClient(), consumerConfig.buildKey());
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, rpcInvocation);
    }
}
