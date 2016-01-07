package com.changxx.srpc.conf;

import com.changxx.srpc.registry.Registry;

/**
 * @author changxx on 2016/1/5.
 */
public abstract class ServiceConfig {

    protected String id;

    protected Registry registry;

    protected String interfaceId;

    protected String alias = "";

    /**
     * 构造关键字方法
     *
     * @return 唯一标识 string
     */
    protected abstract String buildKey();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Registry getRegistry() {
        return registry;
    }

    public void setRegistry(Registry registry) {
        this.registry = registry;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }
}
