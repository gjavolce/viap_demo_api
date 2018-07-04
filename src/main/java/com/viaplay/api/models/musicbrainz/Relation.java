
package com.viaplay.api.models.musicbrainz;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Relation {

    @SerializedName("ended")
    @Expose
    private Boolean ended;
    @SerializedName("target-credit")
    @Expose
    private String targetCredit;
    @SerializedName("begin")
    @Expose
    private Object begin;
    @SerializedName("type-id")
    @Expose
    private String typeId;
    @SerializedName("attributes")
    @Expose
    private List<Object> attributes = null;
    @SerializedName("attribute-values")
    @Expose
    private AttributeValues attributeValues;
    @SerializedName("target-type")
    @Expose
    private String targetType;
    @SerializedName("source-credit")
    @Expose
    private String sourceCredit;
    @SerializedName("end")
    @Expose
    private Object end;
    @SerializedName("url")
    @Expose
    private Url url;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("direction")
    @Expose
    private String direction;

    public Boolean getEnded() {
        return ended;
    }

    public void setEnded(Boolean ended) {
        this.ended = ended;
    }

    public Relation withEnded(Boolean ended) {
        this.ended = ended;
        return this;
    }

    public String getTargetCredit() {
        return targetCredit;
    }

    public void setTargetCredit(String targetCredit) {
        this.targetCredit = targetCredit;
    }

    public Relation withTargetCredit(String targetCredit) {
        this.targetCredit = targetCredit;
        return this;
    }

    public Object getBegin() {
        return begin;
    }

    public void setBegin(Object begin) {
        this.begin = begin;
    }

    public Relation withBegin(Object begin) {
        this.begin = begin;
        return this;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public Relation withTypeId(String typeId) {
        this.typeId = typeId;
        return this;
    }

    public List<Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Object> attributes) {
        this.attributes = attributes;
    }

    public Relation withAttributes(List<Object> attributes) {
        this.attributes = attributes;
        return this;
    }

    public AttributeValues getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(AttributeValues attributeValues) {
        this.attributeValues = attributeValues;
    }

    public Relation withAttributeValues(AttributeValues attributeValues) {
        this.attributeValues = attributeValues;
        return this;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public Relation withTargetType(String targetType) {
        this.targetType = targetType;
        return this;
    }

    public String getSourceCredit() {
        return sourceCredit;
    }

    public void setSourceCredit(String sourceCredit) {
        this.sourceCredit = sourceCredit;
    }

    public Relation withSourceCredit(String sourceCredit) {
        this.sourceCredit = sourceCredit;
        return this;
    }

    public Object getEnd() {
        return end;
    }

    public void setEnd(Object end) {
        this.end = end;
    }

    public Relation withEnd(Object end) {
        this.end = end;
        return this;
    }

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }

    public Relation withUrl(Url url) {
        this.url = url;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Relation withType(String type) {
        this.type = type;
        return this;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Relation withDirection(String direction) {
        this.direction = direction;
        return this;
    }

}
