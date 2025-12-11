package org.xiong.sor.blocks;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public class GeneralParametersBlock {
    private String pdId="GenParams";
    private String lc="CH"; // language code default chinese
    private String cid=""; // cable id, default none
    private String fid=""; // fiber id. defult none
    private short ft=652; // fiber type, 652=conventional smf
    private short nw=1310; // nominal wavelength
    private String ol="";
    private String tl="";
    private String ccd="";
    private String cdf="RC"; // current data flag, three conditions: [NC,RC,OT]
    private long uo=0L; // user offset
    private long uod= 0L; // user offset distance
    private String op=""; // operator
    private String cmt=""; // comment

    public String getPdId() {
        return pdId;
    }

    public void setPdId(String pdId) {
        this.pdId = pdId;
    }

    public String getLc() {
        return lc;
    }

    public void setLc(String lc) {
        this.lc = lc;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public short getFt() {
        return ft;
    }

    public void setFt(short ft) {
        this.ft = ft;
    }

    public short getNw() {
        return nw;
    }

    public void setNw(short nw) {
        this.nw = nw;
    }

    public String getOl() {
        return ol;
    }

    public void setOl(String ol) {
        this.ol = ol;
    }

    public String getTl() {
        return tl;
    }

    public void setTl(String tl) {
        this.tl = tl;
    }

    public String getCcd() {
        return ccd;
    }

    public void setCcd(String ccd) {
        this.ccd = ccd;
    }

    public String getCdf() {
        return cdf;
    }

    public void setCdf(String cdf) {
        this.cdf = cdf;
    }

    public long getUo() {
        return uo;
    }

    public void setUo(long uo) {
        this.uo = uo;
    }

    public long getUod() {
        return uod;
    }

    public void setUod(long uod) {
        this.uod = uod;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }


    public byte[] toBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(256).order(ByteOrder.BIG_ENDIAN);

        byte[] pdIdBytes = pdId.getBytes(StandardCharsets.UTF_8);
        buffer.put((byte)pdIdBytes.length);
        buffer.put(pdIdBytes);
        buffer.put((byte)0);

        byte[] lcBytes = lc.getBytes(StandardCharsets.UTF_8);
        buffer.put((byte)lcBytes.length);
        buffer.put(lcBytes);

        byte[] cidBytes = cid.getBytes(StandardCharsets.UTF_8);
        buffer.put((byte)cidBytes.length);
        buffer.put(cidBytes);
        buffer.put((byte)0);

        byte[] fidBytes = fid.getBytes(StandardCharsets.UTF_8);
        buffer.put((byte)fidBytes.length);
        buffer.put(fidBytes);
        buffer.put((byte)0);

        buffer.putShort(ft);
        buffer.putShort(nw);

        byte[] olBytes = ol.getBytes(StandardCharsets.UTF_8);
        buffer.put((byte)olBytes.length);
        buffer.put(olBytes);
        buffer.put((byte)0);

        byte[] tlBytes = tl.getBytes(StandardCharsets.UTF_8);
        buffer.put((byte)tlBytes.length);
        buffer.put(tlBytes);
        buffer.put((byte)0);

        byte[] ccdBytes = ccd.getBytes(StandardCharsets.UTF_8);
        buffer.put((byte)ccdBytes.length);
        buffer.put(ccdBytes);
        buffer.put((byte)0);

        byte[] cdfBytes = cdf.getBytes(StandardCharsets.UTF_8);
        buffer.put((byte)cdfBytes.length);
        buffer.put(cdfBytes);

        buffer.putLong(uo);
        buffer.putLong(uod);

        byte[] opBytes = op.getBytes(StandardCharsets.UTF_8);
        buffer.put((byte)opBytes.length);
        buffer.put(opBytes);
        buffer.put((byte)0);

        byte[] cmtBytes = cmt.getBytes(StandardCharsets.UTF_8);
        buffer.put((byte)cmtBytes.length);
        buffer.put(cmtBytes);
        buffer.put((byte)0);

        byte[] result = new byte[buffer.position()];
        ((java.nio.Buffer) buffer).flip();
        buffer.get(result);
        return result;
    }

}
