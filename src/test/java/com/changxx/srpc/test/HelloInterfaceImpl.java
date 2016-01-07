package com.changxx.srpc.test;

/**
 * @author changxx on 2016/1/6.
 */
public class HelloInterfaceImpl implements HelloInterface {

    public String echo(String str) {
        System.out.println("request: " + str);
        return "response: " + str;
    }
}
