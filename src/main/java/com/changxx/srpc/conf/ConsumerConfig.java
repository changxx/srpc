package com.changxx.srpc.conf;

import com.changxx.srpc.invocation.RpcInvocation;
import com.changxx.srpc.proxy.Rpc;
import com.changxx.srpc.proxy.RpcProxy;
import com.changxx.srpc.rpc.ConsumerClient;
import com.changxx.srpc.rpc.NettyConsumerClient;

import java.lang.reflect.Proxy;

/**
 * @author changxx on 2016/1/5.
 */
public class ConsumerConfig<T> extends ServiceConfig {

    private Class<T> interfaceClass;

    /**
     * 调用的协议
     */
    private String protocol = "srpc";

    private ConsumerClient consumerClient;

    /**
     * 代理实现类
     */
    private transient volatile T proxyIns;

    public synchronized T refer() {
        if (proxyIns != null) {
            return proxyIns;
        }

        try {
            interfaceClass = (Class<T>) Class.forName(interfaceId, true, Thread.currentThread().getContextClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            consumerClient = new NettyConsumerClient("127.0.0.1", 8050);
            System.out.println("get rpc service " + interfaceClass.getName());
            RpcInvocation rpcInvocation = new RpcInvocation(consumerClient, this.buildKey());
            return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, rpcInvocation);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proxyIns;
    }

    /**
     * Build key.
     *
     * @return the string
     */
    @Override
    public String buildKey() {
        return protocol + "://" + interfaceId + ":" + alias;
    }

    public Class<T> getInterfaceClass() {
        return interfaceClass;
    }

    public void setInterfaceClass(Class<T> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public T getProxyIns() {
        return proxyIns;
    }

    public void setProxyIns(T proxyIns) {
        this.proxyIns = proxyIns;
    }

    public ConsumerClient getConsumerClient() {
        return consumerClient;
    }

    public void setConsumerClient(ConsumerClient consumerClient) {
        this.consumerClient = consumerClient;
    }
}
