
package com.viaplay.api.models.musicbrainz;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MusicBrainzResponse {

    @SerializedName("gender")
    @Expose
    private Object gender;
    @SerializedName("gender-id")
    @Expose
    private Object genderId;
    @SerializedName("isnis")
    @Expose
    private List<String> isnis = null;
    @SerializedName("begin_area")
    @Expose
    private BeginArea beginArea;
    @SerializedName("type-id")
    @Expose
    private String typeId;
    @SerializedName("disambiguation")
    @Expose
    private String disambiguation;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("area")
    @Expose
    private Area area;
    @SerializedName("release-groups")
    @Expose
    private List<ReleaseGroup> releaseGroups = null;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("life-span")
    @Expose
    private LifeSpan lifeSpan;
    @SerializedName("ipis")
    @Expose
    private List<String> ipis = null;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("relations")
    @Expose
    private List<Relation> relations = null;
    @SerializedName("end_area")
    @Expose
    private Object endArea;
    @SerializedName("sort-name")
    @Expose
    private String sortName;
    @SerializedName("type")
    @Expose
    private String type;

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public MusicBrainzResponse withGender(Object gender) {
        this.gender = gender;
        return this;
    }

    public Object getGenderId() {
        return genderId;
    }

    public void setGenderId(Object genderId) {
        this.genderId = genderId;
    }

    public MusicBrainzResponse withGenderId(Object genderId) {
        this.genderId = genderId;
        return this;
    }

    public List<String> getIsnis() {
        return isnis;
    }

    public void setIsnis(List<String> isnis) {
        this.isnis = isnis;
    }

    public MusicBrainzResponse withIsnis(List<String> isnis) {
        this.isnis = isnis;
        return this;
    }

    public BeginArea getBeginArea() {
        return beginArea;
    }

    public void setBeginArea(BeginArea beginArea) {
        this.beginArea = beginArea;
    }

    public MusicBrainzResponse withBeginArea(BeginArea beginArea) {
        this.beginArea = beginArea;
        return this;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public MusicBrainzResponse withTypeId(String typeId) {
        this.typeId = typeId;
        return this;
    }

    public String getDisambiguation() {
        return disambiguation;
    }

    public void setDisambiguation(String disambiguation) {
        this.disambiguation = disambiguation;
    }

    public MusicBrainzResponse withDisambiguation(String disambiguation) {
        this.disambiguation = disambiguation;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MusicBrainzResponse withName(String name) {
        this.name = name;
        return this;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public MusicBrainzResponse withArea(Area area) {
        this.area = area;
        return this;
    }

    public List<ReleaseGroup> getReleaseGroups() {
        return releaseGroups;
    }

    public void setReleaseGroups(List<ReleaseGroup> releaseGroups) {
        this.releaseGroups = releaseGroups;
    }

    public MusicBrainzResponse withReleaseGroups(List<ReleaseGroup> releaseGroups) {
        this.releaseGroups = releaseGroups;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public MusicBrainzResponse withCountry(String country) {
        this.country = country;
        return this;
    }

    public LifeSpan getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(LifeSpan lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public MusicBrainzResponse withLifeSpan(LifeSpan lifeSpan) {
        this.lifeSpan = lifeSpan;
        return this;
    }

    public List<String> getIpis() {
        return ipis;
    }

    public void setIpis(List<String> ipis) {
        this.ipis = ipis;
    }

    public MusicBrainzResponse withIpis(List<String> ipis) {
        this.ipis = ipis;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MusicBrainzResponse withId(String id) {
        this.id = id;
        return this;
    }

    public List<Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

    public MusicBrainzResponse withRelations(List<Relation> relations) {
        this.relations = relations;
        return this;
    }

    public Object getEndArea() {
        return endArea;
    }

    public void setEndArea(Object endArea) {
        this.endArea = endArea;
    }

    public MusicBrainzResponse withEndArea(Object endArea) {
        this.endArea = endArea;
        return this;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public MusicBrainzResponse withSortName(String sortName) {
        this.sortName = sortName;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MusicBrainzResponse withType(String type) {
        this.type = type;
        return this;
    }

}
