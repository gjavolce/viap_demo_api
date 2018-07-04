
package com.viaplay.api.models.coverart;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("types")
    @Expose
    private List<String> types = null;
    @SerializedName("front")
    @Expose
    private Boolean front;
    @SerializedName("back")
    @Expose
    private Boolean back;
    @SerializedName("edit")
    @Expose
    private Integer edit;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("approved")
    @Expose
    private Boolean approved;
    @SerializedName("thumbnails")
    @Expose
    private Thumbnails thumbnails;
    @SerializedName("id")
    @Expose
    private String id;

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public Image withTypes(List<String> types) {
        this.types = types;
        return this;
    }

    public Boolean getFront() {
        return front;
    }

    public void setFront(Boolean front) {
        this.front = front;
    }

    public Image withFront(Boolean front) {
        this.front = front;
        return this;
    }

    public Boolean getBack() {
        return back;
    }

    public void setBack(Boolean back) {
        this.back = back;
    }

    public Image withBack(Boolean back) {
        this.back = back;
        return this;
    }

    public Integer getEdit() {
        return edit;
    }

    public void setEdit(Integer edit) {
        this.edit = edit;
    }

    public Image withEdit(Integer edit) {
        this.edit = edit;
        return this;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Image withImage(String image) {
        this.image = image;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Image withComment(String comment) {
        this.comment = comment;
        return this;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Image withApproved(Boolean approved) {
        this.approved = approved;
        return this;
    }

    public Thumbnails getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }

    public Image withThumbnails(Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Image withId(String id) {
        this.id = id;
        return this;
    }

}
