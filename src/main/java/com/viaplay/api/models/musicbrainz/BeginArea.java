
package com.viaplay.api.models.musicbrainz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BeginArea {

    @SerializedName("sort-name")
    @Expose
    private String sortName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("disambiguation")
    @Expose
    private String disambiguation;

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public BeginArea withSortName(String sortName) {
        this.sortName = sortName;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BeginArea withName(String name) {
        this.name = name;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BeginArea withId(String id) {
        this.id = id;
        return this;
    }

    public String getDisambiguation() {
        return disambiguation;
    }

    public void setDisambiguation(String disambiguation) {
        this.disambiguation = disambiguation;
    }

    public BeginArea withDisambiguation(String disambiguation) {
        this.disambiguation = disambiguation;
        return this;
    }

}
