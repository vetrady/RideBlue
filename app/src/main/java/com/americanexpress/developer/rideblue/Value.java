package com.americanexpress.developer.rideblue;

import java.util.HashMap;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "matchedTo",
        "rideID",
        "rideType",
        "startLat",
        "startLong",
        "destLat",
        "destLong",
        "startLatOther",
        "startLongOther",
        "destLatOther",
        "destLongOther"
})
public class Value {

    @JsonProperty("matchedTo")
    private String matchedTo;
    @JsonProperty("rideID")
    private String rideID;
    @JsonProperty("rideType")
    private String rideType;
    @JsonProperty("startLat")
    private String startLat;
    @JsonProperty("startLong")
    private String startLong;
    @JsonProperty("destLat")
    private String destLat;
    @JsonProperty("destLong")
    private String destLong;
    @JsonProperty("startLatOther")
    private String startLatOther;
    @JsonProperty("startLongOther")
    private String startLongOther;
    @JsonProperty("destLatOther")
    private String destLatOther;
    @JsonProperty("destLongOther")
    private String destLongOther;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("matchedTo")
    public String getMatchedTo() {
        return matchedTo;
    }

    @JsonProperty("matchedTo")
    public void setMatchedTo(String matchedTo) {
        this.matchedTo = matchedTo;
    }

    @JsonProperty("rideID")
    public String getRideID() {
        return rideID;
    }

    @JsonProperty("rideID")
    public void setRideID(String rideID) {
        this.rideID = rideID;
    }

    @JsonProperty("rideType")
    public String getRideType() {
        return rideType;
    }

    @JsonProperty("rideType")
    public void setRideType(String rideType) {
        this.rideType = rideType;
    }

    @JsonProperty("startLat")
    public String getStartLat() {
        return startLat;
    }

    @JsonProperty("startLat")
    public void setStartLat(String startLat) {
        this.startLat = startLat;
    }

    @JsonProperty("startLong")
    public String getStartLong() {
        return startLong;
    }

    @JsonProperty("startLong")
    public void setStartLong(String startLong) {
        this.startLong = startLong;
    }

    @JsonProperty("destLat")
    public String getDestLat() {
        return destLat;
    }

    @JsonProperty("destLat")
    public void setDestLat(String destLat) {
        this.destLat = destLat;
    }

    @JsonProperty("destLong")
    public String getDestLong() {
        return destLong;
    }

    @JsonProperty("destLong")
    public void setDestLong(String destLong) {
        this.destLong = destLong;
    }

    @JsonProperty("startLatOther")
    public String getStartLatOther() {
        return startLatOther;
    }

    @JsonProperty("startLatOther")
    public void setStartLatOther(String startLatOther) {
        this.startLatOther = startLatOther;
    }

    @JsonProperty("startLongOther")
    public String getStartLongOther() {
        return startLongOther;
    }

    @JsonProperty("startLongOther")
    public void setStartLongOther(String startLongOther) {
        this.startLongOther = startLongOther;
    }

    @JsonProperty("destLatOther")
    public String getDestLatOther() {
        return destLatOther;
    }

    @JsonProperty("destLatOther")
    public void setDestLatOther(String destLatOther) {
        this.destLatOther = destLatOther;
    }

    @JsonProperty("destLongOther")
    public String getDestLongOther() {
        return destLongOther;
    }

    @JsonProperty("destLongOther")
    public void setDestLongOther(String destLongOther) {
        this.destLongOther = destLongOther;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}