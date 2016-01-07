package com.changxx.srpc.registry;

/**
 * 注册中心
 *
 * @author changxx on 2016/1/5.
 */
public interface Registry {

    /**
     * 注册服务
     */
    void register();

    /**
     * 取消注册服务
     */
    void unregister();

    /**
     * 发现服务
     */
    void lookup(String serviceKey);

    /**
     * 服务消费端服务订阅
     */
    void subscribe(String serviceKey);

    /**
     * 获取服务列表
     */
    String get(String serviceKey);

}
