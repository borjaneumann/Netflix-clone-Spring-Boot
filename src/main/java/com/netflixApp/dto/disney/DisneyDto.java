
package com.netflixApp.dto.disney;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "description",
    "headquarters",
    "homepage",
    "id",
    "logo_path",
    "name",
    "origin_country",
    "parent_company",
    "movies"
})
public class DisneyDto {

    @JsonProperty("description")
    private String description;
    @JsonProperty("headquarters")
    private String headquarters;
    @JsonProperty("homepage")
    private String homepage;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("logo_path")
    private String logoPath;
    @JsonProperty("name")
    private String name;
    @JsonProperty("origin_country")
    private String originCountry;
    @JsonProperty("parent_company")
    private ParentCompany parentCompany;
    @JsonProperty("movies")
    private Movies movies;

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("headquarters")
    public String getHeadquarters() {
        return headquarters;
    }

    @JsonProperty("headquarters")
    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    @JsonProperty("homepage")
    public String getHomepage() {
        return homepage;
    }

    @JsonProperty("homepage")
    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("logo_path")
    public String getLogoPath() {
        return logoPath;
    }

    @JsonProperty("logo_path")
    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("origin_country")
    public String getOriginCountry() {
        return originCountry;
    }

    @JsonProperty("origin_country")
    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    @JsonProperty("parent_company")
    public ParentCompany getParentCompany() {
        return parentCompany;
    }

    @JsonProperty("parent_company")
    public void setParentCompany(ParentCompany parentCompany) {
        this.parentCompany = parentCompany;
    }

    @JsonProperty("movies")
    public Movies getMovies() {
        return movies;
    }

    @JsonProperty("movies")
    public void setMovies(Movies movies) {
        this.movies = movies;
    }

}
