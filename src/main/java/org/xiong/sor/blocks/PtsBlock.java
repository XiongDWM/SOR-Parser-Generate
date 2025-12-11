package org.xiong.sor.blocks;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

import org.xiong.sor.UnsignedConvert;

public class PtsBlock {
    private String dpid="DataPts";
    private long tndp; // 点数
    private short tsf=1;
    private int[] sf; // 序列化的时候需要转为无符号short

    public String getDpid() {
        return dpid;
    }

    public void setDpid(String dpid) {
        this.dpid = dpid;
    }

    public long getTndp() {
        return tndp;
    }

    public void setTndp(long tndp) {
        this.tndp = tndp;
    }

    public short getTsf() {
        return tsf;
    }

    public void setTsf(short tsf) {
        this.tsf = tsf;
    }

    public int[] getSf() {
        return sf;
    }

    public void setSf(int[] sf) {
        this.sf = sf;
    }

    public byte[] toBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(1024).order(ByteOrder.BIG_ENDIAN);
        byte[] dpidBytes = dpid.getBytes(StandardCharsets.UTF_8);
        buffer.put((byte)dpidBytes.length);
        buffer.put(dpidBytes);
        buffer.put((byte)0);
        buffer.putLong(tndp);
        buffer.putShort(tsf);
        if (sf != null) {
            buffer.put((byte)sf.length);
            for (int s : sf) {
                buffer.put(UnsignedConvert.intToUnsignedShortBytes(s));
            }
        } else {
            buffer.put((byte)0);
        }
        byte[] result = new byte[buffer.position()];
        ((java.nio.Buffer) buffer).flip();
        buffer.get(result);
        return result;
    }
}
