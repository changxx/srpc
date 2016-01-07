package com.changxx.srpc.conf;

import com.changxx.srpc.proxy.Rpc;
import com.changxx.srpc.proxy.RpcProxy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author changxx on 2016/1/5.
 */
public class ProviderConfig<T> extends ServiceConfig {

    /**
     * Provider容器
     */
    public static Map<String, Object> serviceMap = new ConcurrentHashMap<String, Object>();

    private T ref;

    /**
     * 调用的协议
     */
    protected String protocol = "srpc";

    @Override
    protected String buildKey() {
        return protocol + "://" + interfaceId + ":" + alias;
    }

    public void export() {
        ProviderConfig.serviceMap.put(this.buildKey(), ref);
        Rpc<T> rpc = new RpcProxy<T>();
        try {
            rpc.export(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public T getRef() {
        return ref;
    }

    public void setRef(T ref) {
        this.ref = ref;
    }
}
