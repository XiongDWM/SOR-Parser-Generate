package org.xiong.sor;


import org.xiong.sor.blocks.SorFullProtocol;

public abstract class GenBlockMethods<T> {
    public abstract SorFullProtocol convertSorProtocol(T origin);

    public static byte[] getProtocolBytes(SorFullProtocol protocol){
        return protocol.toBytes();
    }
}
