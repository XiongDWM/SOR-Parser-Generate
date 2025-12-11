package org.xiong.sor;

import java.math.BigInteger;

public class UnsignedConvert {
    public static byte[] intToUnsignedShortBytes(int v){
        if(v<0||v>0Xffff){
            throw new IllegalArgumentException("out of range: unsigned short(0-65535)");
        }
        byte[] rs= new byte[2];
        rs[0]=(byte)((v>>8)&0xff);
        rs[1]=(byte)(v&0xff);
        return rs;
    }

    public static byte[] lonngToUsignedIntBytes(long v){
        if(v<0||v>0XffffffffL){
            throw new IllegalArgumentException("out of range: unsigned int(0-4294967295)");
        }
        byte[] rs= new byte[4];
        rs[0]=(byte)((v>>24)&0xff);
        rs[1]=(byte)((v>>16)&0xff);
        rs[2]=(byte)((v>>8)&0xff);
        rs[3]=(byte)(v&0xff);
        return rs;
    }

    public static byte[] BigIntToUnsignedLongBytes(BigInteger value) {
        if(value.signum() < 0 || value.compareTo(BigInteger.valueOf(0xFFFFFFFFFFFFFFFFL)) > 0) {
            throw new IllegalArgumentException("out of range: unsigned long(0-18446744073709551615)");
        }
        byte[] bytes = new byte[8];
        for (int i = 7; i >= 0; i--) {
            bytes[i] = (byte)(value.intValue() & 0xFF);
            value = value.shiftRight(8);
        }
        return bytes;
    }


}
