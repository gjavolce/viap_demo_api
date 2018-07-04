
package com.viaplay.api.models.discogs;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DiscogsResponse {

    @SerializedName("profile")
    @Expose
    private String profile;
    @SerializedName("urls")
    @Expose
    private List<String> urls = null;
    @SerializedName("releases_url")
    @Expose
    private String releasesUrl;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("realname")
    @Expose
    private String realname;
    @SerializedName("groups")
    @Expose
    private List<Group> groups = null;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("resource_url")
    @Expose
    private String resourceUrl;
    @SerializedName("aliases")
    @Expose
    private List<Alias> aliases = null;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("data_quality")
    @Expose
    private String dataQuality;
    @SerializedName("namevariations")
    @Expose
    private List<String> namevariations = null;

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public DiscogsResponse withProfile(String profile) {
        this.profile = profile;
        return this;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public DiscogsResponse withUrls(List<String> urls) {
        this.urls = urls;
        return this;
    }

    public String getReleasesUrl() {
        return releasesUrl;
    }

    public void setReleasesUrl(String releasesUrl) {
        this.releasesUrl = releasesUrl;
    }

    public DiscogsResponse withReleasesUrl(String releasesUrl) {
        this.releasesUrl = releasesUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DiscogsResponse withName(String name) {
        this.name = name;
        return this;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public DiscogsResponse withUri(String uri) {
        this.uri = uri;
        return this;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public DiscogsResponse withRealname(String realname) {
        this.realname = realname;
        return this;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public DiscogsResponse withGroups(List<Group> groups) {
        this.groups = groups;
        return this;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public DiscogsResponse withImages(List<Image> images) {
        this.images = images;
        return this;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public DiscogsResponse withResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
        return this;
    }

    public List<Alias> getAliases() {
        return aliases;
    }

    public void setAliases(List<Alias> aliases) {
        this.aliases = aliases;
    }

    public DiscogsResponse withAliases(List<Alias> aliases) {
        this.aliases = aliases;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DiscogsResponse withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getDataQuality() {
        return dataQuality;
    }

    public void setDataQuality(String dataQuality) {
        this.dataQuality = dataQuality;
    }

    public DiscogsResponse withDataQuality(String dataQuality) {
        this.dataQuality = dataQuality;
        return this;
    }

    public List<String> getNamevariations() {
        return namevariations;
    }

    public void setNamevariations(List<String> namevariations) {
        this.namevariations = namevariations;
    }

    public DiscogsResponse withNamevariations(List<String> namevariations) {
        this.namevariations = namevariations;
        return this;
    }

}
