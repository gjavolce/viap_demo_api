
package com.viaplay.api.models.musicbrainz;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Area {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("disambiguation")
    @Expose
    private String disambiguation;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("sort-name")
    @Expose
    private String sortName;
    @SerializedName("iso-3166-1-codes")
    @Expose
    private List<String> iso31661Codes = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Area withId(String id) {
        this.id = id;
        return this;
    }

    public String getDisambiguation() {
        return disambiguation;
    }

    public void setDisambiguation(String disambiguation) {
        this.disambiguation = disambiguation;
    }

    public Area withDisambiguation(String disambiguation) {
        this.disambiguation = disambiguation;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Area withName(String name) {
        this.name = name;
        return this;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public Area withSortName(String sortName) {
        this.sortName = sortName;
        return this;
    }

    public List<String> getIso31661Codes() {
        return iso31661Codes;
    }

    public void setIso31661Codes(List<String> iso31661Codes) {
        this.iso31661Codes = iso31661Codes;
    }

    public Area withIso31661Codes(List<String> iso31661Codes) {
        this.iso31661Codes = iso31661Codes;
        return this;
    }

}
