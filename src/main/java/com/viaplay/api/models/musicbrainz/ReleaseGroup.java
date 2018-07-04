
package com.viaplay.api.models.musicbrainz;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReleaseGroup {

    @SerializedName("secondary-types")
    @Expose
    private List<String> secondaryTypes = null;
    @SerializedName("secondary-type-ids")
    @Expose
    private List<String> secondaryTypeIds = null;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("disambiguation")
    @Expose
    private String disambiguation;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("first-release-date")
    @Expose
    private String firstReleaseDate;
    @SerializedName("primary-type-id")
    @Expose
    private String primaryTypeId;
    @SerializedName("primary-type")
    @Expose
    private String primaryType;

    public List<String> getSecondaryTypes() {
        return secondaryTypes;
    }

    public void setSecondaryTypes(List<String> secondaryTypes) {
        this.secondaryTypes = secondaryTypes;
    }

    public ReleaseGroup withSecondaryTypes(List<String> secondaryTypes) {
        this.secondaryTypes = secondaryTypes;
        return this;
    }

    public List<String> getSecondaryTypeIds() {
        return secondaryTypeIds;
    }

    public void setSecondaryTypeIds(List<String> secondaryTypeIds) {
        this.secondaryTypeIds = secondaryTypeIds;
    }

    public ReleaseGroup withSecondaryTypeIds(List<String> secondaryTypeIds) {
        this.secondaryTypeIds = secondaryTypeIds;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ReleaseGroup withId(String id) {
        this.id = id;
        return this;
    }

    public String getDisambiguation() {
        return disambiguation;
    }

    public void setDisambiguation(String disambiguation) {
        this.disambiguation = disambiguation;
    }

    public ReleaseGroup withDisambiguation(String disambiguation) {
        this.disambiguation = disambiguation;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ReleaseGroup withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getFirstReleaseDate() {
        return firstReleaseDate;
    }

    public void setFirstReleaseDate(String firstReleaseDate) {
        this.firstReleaseDate = firstReleaseDate;
    }

    public ReleaseGroup withFirstReleaseDate(String firstReleaseDate) {
        this.firstReleaseDate = firstReleaseDate;
        return this;
    }

    public String getPrimaryTypeId() {
        return primaryTypeId;
    }

    public void setPrimaryTypeId(String primaryTypeId) {
        this.primaryTypeId = primaryTypeId;
    }

    public ReleaseGroup withPrimaryTypeId(String primaryTypeId) {
        this.primaryTypeId = primaryTypeId;
        return this;
    }

    public String getPrimaryType() {
        return primaryType;
    }

    public void setPrimaryType(String primaryType) {
        this.primaryType = primaryType;
    }

    public ReleaseGroup withPrimaryType(String primaryType) {
        this.primaryType = primaryType;
        return this;
    }

}
