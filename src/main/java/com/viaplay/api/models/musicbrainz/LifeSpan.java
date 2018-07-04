
package com.viaplay.api.models.musicbrainz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LifeSpan {

    @SerializedName("begin")
    @Expose
    private String begin;
    @SerializedName("end")
    @Expose
    private Object end;
    @SerializedName("ended")
    @Expose
    private Boolean ended;

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public LifeSpan withBegin(String begin) {
        this.begin = begin;
        return this;
    }

    public Object getEnd() {
        return end;
    }

    public void setEnd(Object end) {
        this.end = end;
    }

    public LifeSpan withEnd(Object end) {
        this.end = end;
        return this;
    }

    public Boolean getEnded() {
        return ended;
    }

    public void setEnded(Boolean ended) {
        this.ended = ended;
    }

    public LifeSpan withEnded(Boolean ended) {
        this.ended = ended;
        return this;
    }

}
