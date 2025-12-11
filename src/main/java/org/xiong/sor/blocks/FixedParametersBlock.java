package org.xiong.sor.blocks;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

import org.xiong.sor.UnsignedConvert;

public class FixedParametersBlock {
    private String fpid="";
    private BigInteger dts=new BigInteger(Long.toUnsignedString(System.currentTimeMillis())); // current time
    private String ud="mt"; // units of distance default meter
    private short aw= 1310; // actual wavelength
    private short ao=0;
    private long aod=0L;
    private short tpw=1;
    private short[] pwu=new short[1]; // length = tpw
    private long[] ds=new long[1];
    private long[] nppw=new long[1];
    private long gi=0L;
    private short bc=800;
    private long nav=0L;
    private short at=0; // unsigned short
    private long ar=0L;
    private long ard=0L;
    private long fpo=0L;
    private int nf=65535; // unsigned short
    private short nfsf=1000;
    private int po=0; // unsigned short
    private int rt=55000; // unsigned short
    private int et=3000; // unsigned short
    private String tt="ST";
    private long[] wc=new long[4];

    public String getFpid() {
        return fpid;
    }

    public void setFpid(String fpid) {
        this.fpid = fpid;
    }

    public BigInteger getDts() {
        return dts;
    }

    public void setDts(BigInteger dts) {
        this.dts = dts;
    }

    public String getUd() {
        return ud;
    }

    public void setUd(String ud) {
        this.ud = ud;
    }

    public short getAw() {
        return aw;
    }

    public void setAw(short aw) {
        this.aw = aw;
    }

    public short getAo() {
        return ao;
    }

    public void setAo(short ao) {
        this.ao = ao;
    }

    public long getAod() {
        return aod;
    }

    public void setAod(long aod) {
        this.aod = aod;
    }

    public short getTpw() {
        return tpw;
    }

    public void setTpw(short tpw) {
        this.tpw = tpw;
    }

    public short[] getPwu() {
        return pwu;
    }

    public void setPwu(short[] pwu) {
        this.pwu = pwu;
    }

    public long[] getDs() {
        return ds;
    }

    public void setDs(long[] ds) {
        this.ds = ds;
    }

    public long[] getNppw() {
        return nppw;
    }

    public void setNppw(long[] nppw) {
        this.nppw = nppw;
    }

    public long getGi() {
        return gi;
    }

    public void setGi(long gi) {
        this.gi = gi;
    }

    public short getBc() {
        return bc;
    }

    public void setBc(short bc) {
        this.bc = bc;
    }

    public long getNav() {
        return nav;
    }

    public void setNav(long nav) {
        this.nav = nav;
    }

    public short getAt() {
        return at;
    }

    public void setAt(short at) {
        this.at = at;
    }

    public long getAr() {
        return ar;
    }

    public void setAr(long ar) {
        this.ar = ar;
    }

    public long getArd() {
        return ard;
    }

    public void setArd(long ard) {
        this.ard = ard;
    }

    public long getFpo() {
        return fpo;
    }

    public void setFpo(long fpo) {
        this.fpo = fpo;
    }

    public int getNf() {
        return nf;
    }

    public void setNf(int nf) {
        this.nf = nf;
    }

    public short getNfsf() {
        return nfsf;
    }

    public void setNfsf(short nfsf) {
        this.nfsf = nfsf;
    }

    public int getPo() {
        return po;
    }

    public void setPo(int po) {
        this.po = po;
    }

    public int getRt() {
        return rt;
    }

    public void setRt(int rt) {
        this.rt = rt;
    }

    public int getEt() {
        return et;
    }

    public void setEt(int et) {
        this.et = et;
    }

    public String getTt() {
        return tt;
    }

    public void setTt(String tt) {
        this.tt = tt;
    }

    public long[] getWc() {
        return wc;
    }

    public void setWc(long[] wc) {
        this.wc = wc;
    }
    public byte[] toBytes() {
        // 估算总长度，实际可根据协议调整
        ByteBuffer buffer = ByteBuffer.allocate(1024).order(ByteOrder.BIG_ENDIAN);

        // String转byte，假定定长或前加长度
        byte[] fpidBytes = fpid.getBytes(StandardCharsets.UTF_8);
        buffer.put((byte)fpidBytes.length);
        buffer.put(fpidBytes);
        buffer.put((byte)0);

        byte[] dtsBytes = UnsignedConvert.BigIntToUnsignedLongBytes(dts);
        buffer.put(dtsBytes);

        byte[] udBytes = ud.getBytes(StandardCharsets.UTF_8);
        buffer.put((byte)udBytes.length);
        buffer.put(udBytes);

        buffer.putShort(aw);
        buffer.putShort(ao);
        buffer.putLong(aod);
        buffer.putShort(tpw);

        for (short p : pwu) buffer.putShort(p);
        for (long d : ds) buffer.putLong(d);
        for (long n : nppw) buffer.putLong(n);

        buffer.putLong(gi);
        buffer.putShort(bc);
        buffer.putLong(nav);
        buffer.putShort(at);
        buffer.putLong(ar);
        buffer.putLong(ard);
        buffer.putLong(fpo);

        buffer.put(UnsignedConvert.intToUnsignedShortBytes(nf));
        buffer.putShort(nfsf);
        buffer.put(UnsignedConvert.intToUnsignedShortBytes(po));
        buffer.put(UnsignedConvert.intToUnsignedShortBytes(rt));
        buffer.put(UnsignedConvert.intToUnsignedShortBytes(et));

        byte[] ttBytes = tt.getBytes(StandardCharsets.UTF_8);
        buffer.put((byte)ttBytes.length);
        buffer.put(ttBytes);
        for (long w : wc) buffer.putLong(w);

        // 截取实际长度
        byte[] result = new byte[buffer.position()];
        ((java.nio.Buffer) buffer).flip();
        buffer.get(result);
        return result;
    }
    
}
