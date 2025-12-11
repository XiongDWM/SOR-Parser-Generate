package org.xiong.sor.blocks;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class KeyEventsBlock {
    public static class Event{
        short en=1; // event number
        long ept=0; // event propagation time
        short aci=0; // attenuation coefficient lead-in fiber
        short el=0; // event loss
        long er; // event reflectance
        String ec; // event code 6 bytes
        String lmt; // loss measurement technique 2 bytes
        long[] ml=new long[5]; // marker locations size=5
        String cmt=""; // comment

        
        public byte[] toBytes() {
            ByteBuffer buffer = ByteBuffer.allocate(256).order(ByteOrder.BIG_ENDIAN);

            buffer.putShort(en);
            buffer.putLong(ept);
            buffer.putShort(aci);
            buffer.putShort(el);
            buffer.putLong(er);

            // ec: 固定6字节
            byte[] ecBytes = ec == null ? new byte[6] : ec.getBytes(StandardCharsets.UTF_8);
            if (ecBytes.length < 6) {
                byte[] tmp = new byte[6];
                System.arraycopy(ecBytes, 0, tmp, 0, ecBytes.length);
                ecBytes = tmp;
            }
            buffer.put(ecBytes, 0, 6);

            // lmt: 固定2字节
            byte[] lmtBytes = lmt == null ? new byte[2] : lmt.getBytes(StandardCharsets.UTF_8);
            if (lmtBytes.length < 2) {
                byte[] tmp = new byte[2];
                System.arraycopy(lmtBytes, 0, tmp, 0, lmtBytes.length);
                lmtBytes = tmp;
            }
            buffer.put(lmtBytes, 0, 2);

            // ml: 5个long
            for (long m : ml) buffer.putLong(m);

            // cmt: 长度+内容+null terminator
            byte[] cmtBytes = cmt.getBytes(StandardCharsets.UTF_8);
            buffer.put((byte)cmtBytes.length);
            buffer.put(cmtBytes);
            buffer.put((byte)0);

            byte[] result = new byte[buffer.position()];
            ((java.nio.Buffer) buffer).flip();
            buffer.get(result);
            return result;
        }


        public void setEn(short en) {
            this.en = en;
        }


        public void setEpt(long ept) {
            this.ept = ept;
        }


        public void setAci(short aci) {
            this.aci = aci;
        }


        public void setEl(short el) {
            this.el = el;
        }


        public void setEr(long er) {
            this.er = er;
        }


        public void setEc(String ec) {
            this.ec = ec;
        }


        public void setLmt(String lmt) {
            this.lmt = lmt;
        }


        public void setMl(long[] ml) {
            this.ml = ml;
        }


        public void setCmt(String cmt) {
            this.cmt = cmt;
        }

    }
    private String keId="KeyEvents";
    private short tnke;
    private List<Event> events;
    private long eel=0; // end-to-end loss
    private long[] rlmp=new long[2]; // optical return loss marker position

    public String getKeId() {
        return keId;
    }

    public void setKeId(String keId) {
        this.keId = keId;
    }

    public short getTnke() {
        return tnke;
    }

    public void setTnke(short tnke) {
        this.tnke = tnke;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public long getEel() {
        return eel;
    }

    public void setEel(long eel) {
        this.eel = eel;
    }

    public long[] getRlmp() {
        return rlmp;
    }

    public void setRlmp(long[] rlmp) {
        this.rlmp = rlmp;
    }

    public byte[] toBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // keId
        byte[] keIdBytes = keId.getBytes(StandardCharsets.UTF_8);
        buffer.put((byte)keIdBytes.length);
        buffer.put(keIdBytes);
        buffer.put((byte)0);

        // tnke
        buffer.putShort(tnke);

        // events
        if (events != null) {
            buffer.put((byte)events.size());
            for (Event e : events) {
                buffer.put(e.toBytes());
            }
        } else {
            buffer.put((byte)0);
        }

        // eel
        buffer.putLong(eel);

        // rlmp
        for (long v : rlmp) buffer.putLong(v);  
        

        byte[] result = new byte[buffer.position()];
        ((java.nio.Buffer) buffer).flip();
        buffer.get(result);
        return result;
    }

    public void addEvent(Event event) {
        if (events == null) {
            events = new ArrayList<>();
        }
        events.add(event);
    }
}
