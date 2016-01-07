package com.changxx.srpc.proxy;

import com.changxx.srpc.conf.ConsumerConfig;
import com.changxx.srpc.conf.ProviderConfig;

/**
 * @author changxx on 2016/1/6.
 */
public interface Rpc<T> {

    /**
     * 暴露服务
     *
     * @throws Exception
     */
    void export(ProviderConfig<T> providerConfig) throws Exception;

    /**
     * 引用服务
     *
     * @throws Exception
     */
    T refer(ConsumerConfig<T> consumerConfig) throws Exception;
}
