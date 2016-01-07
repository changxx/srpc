package com.changxx.srpc.codec;

public interface Encoder {

    byte[] encode(Object obj);

    byte[] encode(Object obj, String classTypeName);

}