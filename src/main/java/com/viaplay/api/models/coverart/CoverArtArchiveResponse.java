
package com.viaplay.api.models.coverart;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoverArtArchiveResponse {

    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("release")
    @Expose
    private String release;

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public CoverArtArchiveResponse withImages(List<Image> images) {
        this.images = images;
        return this;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public CoverArtArchiveResponse withRelease(String release) {
        this.release = release;
        return this;
    }

}
