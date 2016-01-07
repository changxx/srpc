package com.changxx.srpc.codec;

public interface Decoder {

    Object decode(byte[] datas, Class clazz);

    Object decode(byte[] datas, String className);

}