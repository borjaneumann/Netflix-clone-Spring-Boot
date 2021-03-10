package com.netflixApp.dto.images;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({
        "id",
        "backdrops",
        "posters"
})
public class MovieImagesDto {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("backdrops")
    private List<Backdrop> backdrops = null;
    @JsonProperty("posters")
    private List<Poster> posters = null;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("backdrops")
    public List<Backdrop> getBackdrops() {
        return backdrops;
    }

    @JsonProperty("backdrops")
    public void setBackdrops(List<Backdrop> backdrops) {
        this.backdrops = backdrops;
    }

    @JsonProperty("posters")
    public List<Poster> getPosters() {
        return posters;
    }

    @JsonProperty("posters")
    public void setPosters(List<Poster> posters) {
        this.posters = posters;
    }

}
