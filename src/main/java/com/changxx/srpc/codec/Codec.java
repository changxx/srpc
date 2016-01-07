package com.changxx.srpc.codec;

public interface Codec extends Encoder, Decoder {

    /**
     * 空的Object数组，无参方法
     */
    public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];

    /**
     * 空的Class数组，无参方法
     */
    public static final Class<?>[] EMPTY_CLASS_ARRAY = new Class<?>[0];

}