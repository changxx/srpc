package com.changxx.srpc.test;

import com.changxx.srpc.conf.ProviderConfig;

/**
 * @author changxx on 2016/1/6.
 */
public class ProviderMainTest {

    public static void main(String[] args) {
        ProviderConfig<HelloInterface> providerConfig = new ProviderConfig<HelloInterface>();
        providerConfig.setInterfaceId("com.changxx.srpc.test.HelloInterface");
        providerConfig.setAlias("changxx");
        HelloInterface provider = new HelloInterfaceImpl();
        providerConfig.setRef(provider);

        providerConfig.export();

        System.out.println("service started");
    }

}
