package org.xiong.sor.blocks;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import org.xiong.sor.UnsignedConvert;

public class MapBlock {
    static class BlockMeta{
        String bid; // block id
        int reversionId=0; // unsigned short
        long size=0;

        public BlockMeta(){

        }

        public BlockMeta(String bid, int reversionId, long size) {
            this.bid = bid;
            this.reversionId = reversionId;
            this.size = size;
        }
        
        public byte[] toBytes() {
            byte[] bidBytes = bid.getBytes();
            ByteBuffer buffer = ByteBuffer.allocate(bidBytes.length + 2 + 4).order(ByteOrder.BIG_ENDIAN); 

            buffer.put(bidBytes);
            buffer.put(UnsignedConvert.intToUnsignedShortBytes(reversionId)); // unsigned short 2 bytes
            buffer.putLong(size); 

            byte[] result = new byte[buffer.position()];
            ((java.nio.Buffer) buffer).flip();
            buffer.get(result);
            return result;
        }

        @Override
        public String toString() {
            return "BlockMeta [bid=" + bid + ", reversionId=" + reversionId + ", size=" + size + "]";
        }
        
    }
    private String mbId="Map";
    private int mrn=0; // unsigned short
    private long mbs=0L; 
    private short nb=5; // number of block meta info, default 5
    private BlockMeta[] metaInfo;

    public String getMbId() {
        return mbId;
    }

    public void setMbId(String mbId) {
        this.mbId = mbId;
    }

    public int getMrn() {
        return mrn;
    }

    public void setMrn(int mrn) {
        this.mrn = mrn;
    }

    public long getMbs() {
        return mbs;
    }

    public void setMbs(long mbs) {
        this.mbs = mbs;
    }

    public short getNb() {
        return nb;
    }

    public void setNb(short nb) {
        this.nb = nb;
    }

    public BlockMeta[] getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(BlockMeta[] metaInfo) {
        this.metaInfo = metaInfo;
    }

    public byte[] toBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(1024).order(ByteOrder.BIG_ENDIAN);

        byte[] mbIdBytes = mbId.getBytes();
        buffer.put((byte)mbIdBytes.length);
        buffer.put(mbIdBytes);
        buffer.put((byte)0);

        buffer.put(UnsignedConvert.intToUnsignedShortBytes(mrn));

        buffer.putLong(mbs);

        buffer.putShort(nb);

        buffer.putShort((short)metaInfo.length);

        byte[] result = new byte[buffer.position()];
        
        ((java.nio.Buffer) buffer).flip();
        buffer.get(result);
        return result;
    }

    @Override
    public String toString() {
        return "MapBlock [mbId=" + mbId + ", mrn=" + mrn + ", mbs=" + mbs + ", nb=" + nb + ", metaInfo="
                + Arrays.toString(metaInfo) + "]";
    }

    
}
