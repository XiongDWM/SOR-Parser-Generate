package org.xiong.sor.blocks;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class SupplierParametersBlock {
    private String spId="SupParams";
    private String sn="";
    private String mfId="";
    private String otdr="";
    private String omId="";
    private String omsn="";
    private String sr="";
    private String ot="";

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getMfId() {
        return mfId;
    }

    public void setMfId(String mfId) {
        this.mfId = mfId;
    }

    public String getOtdr() {
        return otdr;
    }

    public void setOtdr(String otdr) {
        this.otdr = otdr;
    }

    public String getOmId() {
        return omId;
    }

    public void setOmId(String omId) {
        this.omId = omId;
    }

    public String getOmsn() {
        return omsn;
    }

    public void setOmsn(String omsn) {
        this.omsn = omsn;
    }

    public String getSr() {
        return sr;
    }

    public void setSr(String sr) {
        this.sr = sr;
    }

    public String getOt() {
        return ot;
    }

    public void setOt(String ot) {
        this.ot = ot;
    }

    public byte[] toBytes(){
        ByteBuffer buffer = ByteBuffer.allocate(256);
        byte[] spIdBytes = spId.getBytes(StandardCharsets.UTF_8);
        buffer.put((byte)spIdBytes.length);
        buffer.put(spIdBytes);
        buffer.put((byte)0);
        byte[] snBytes = sn.getBytes(StandardCharsets.UTF_8);
        buffer.put((byte)snBytes.length);
        buffer.put(snBytes);
        buffer.put((byte)0);
        byte[] mfIdBytes = mfId.getBytes(StandardCharsets.UTF_8);
        buffer.put((byte)mfIdBytes.length);
        buffer.put(mfIdBytes);
        buffer.put((byte)0);
        byte[] otdrBytes = otdr.getBytes(StandardCharsets.UTF_8);
        buffer.put((byte)otdrBytes.length);
        buffer.put(otdrBytes);
        buffer.put((byte)0);
        byte[] omIdBytes = omId.getBytes(StandardCharsets.UTF_8);
        buffer.put((byte)omIdBytes.length);
        buffer.put(omIdBytes);
        buffer.put((byte)0);
        byte[] omsnBytes = omsn.getBytes(StandardCharsets.UTF_8);
        buffer.put((byte)omsnBytes.length);
        buffer.put(omsnBytes);
        buffer.put((byte)0);
        byte[] srBytes = sr.getBytes(StandardCharsets.UTF_8);
        buffer.put((byte)srBytes.length);
        buffer.put(srBytes);
        buffer.put((byte)0);
        byte[] otBytes = ot.getBytes(StandardCharsets.UTF_8);
        buffer.put((byte)otBytes.length); 
        buffer.put(otBytes);
        buffer.put((byte)0);
        byte[] result = new byte[buffer.position()];
        ((java.nio.Buffer) buffer).flip();
        buffer.get(result);
        return result;
    }
}
