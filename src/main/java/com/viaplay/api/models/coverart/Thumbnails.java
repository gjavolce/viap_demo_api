
package com.viaplay.api.models.coverart;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Thumbnails {

    @SerializedName("large")
    @Expose
    private String large;
    @SerializedName("small")
    @Expose
    private String small;

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public Thumbnails withLarge(String large) {
        this.large = large;
        return this;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public Thumbnails withSmall(String small) {
        this.small = small;
        return this;
    }

}
