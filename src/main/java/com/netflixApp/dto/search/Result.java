
package com.netflixApp.dto.search;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "adult",
    "gender",
    "id",
    "known_for",
    "known_for_department",
    "media_type",
    "name",
    "popularity",
    "profile_path"
})
public class Result {

    @JsonProperty("adult")
    private Boolean adult;
    @JsonProperty("gender")
    private Integer gender;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("known_for")
    private List<KnownFor> knownFor = null;
    @JsonProperty("known_for_department")
    private String knownForDepartment;
    @JsonProperty("media_type")
    private String mediaType;
    @JsonProperty("name")
    private String name;
    @JsonProperty("popularity")
    private Float popularity;
    @JsonProperty("profile_path")
    private Object profilePath;

    @JsonProperty("adult")
    public Boolean getAdult() {
        return adult;
    }

    @JsonProperty("adult")
    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    @JsonProperty("gender")
    public Integer getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("known_for")
    public List<KnownFor> getKnownFor() {
        return knownFor;
    }

    @JsonProperty("known_for")
    public void setKnownFor(List<KnownFor> knownFor) {
        this.knownFor = knownFor;
    }

    @JsonProperty("known_for_department")
    public String getKnownForDepartment() {
        return knownForDepartment;
    }

    @JsonProperty("known_for_department")
    public void setKnownForDepartment(String knownForDepartment) {
        this.knownForDepartment = knownForDepartment;
    }

    @JsonProperty("media_type")
    public String getMediaType() {
        return mediaType;
    }

    @JsonProperty("media_type")
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("popularity")
    public Float getPopularity() {
        return popularity;
    }

    @JsonProperty("popularity")
    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }

    @JsonProperty("profile_path")
    public Object getProfilePath() {
        return profilePath;
    }

    @JsonProperty("profile_path")
    public void setProfilePath(Object profilePath) {
        this.profilePath = profilePath;
    }

}
