
package com.viaplay.api.models.discogs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("resource_url")
    @Expose
    private String resourceUrl;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("uri150")
    @Expose
    private String uri150;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Image withUri(String uri) {
        this.uri = uri;
        return this;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Image withHeight(Integer height) {
        this.height = height;
        return this;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Image withWidth(Integer width) {
        this.width = width;
        return this;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public Image withResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Image withType(String type) {
        this.type = type;
        return this;
    }

    public String getUri150() {
        return uri150;
    }

    public void setUri150(String uri150) {
        this.uri150 = uri150;
    }

    public Image withUri150(String uri150) {
        this.uri150 = uri150;
        return this;
    }

}
