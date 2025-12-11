package org.xiong;

public class SorSample {
    private int[] curve;
    private String eventComment;

    public SorSample() {
    }
    public SorSample(int[] curve, String eventComment) {
        this.curve = curve;
        this.eventComment = eventComment;
    }

    public int[] getCurve() {
        return curve;
    }

    public String getEventComment() {
        return eventComment;
    }
}