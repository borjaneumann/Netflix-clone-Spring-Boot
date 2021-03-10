package com.netflixApp.view;


import com.netflixApp.dto.filmDetailKaart.Genre;

import java.util.List;

public class YearsDetailsList2 {
    private String backdropPath;
    private String overview;
    private Integer id;
    private String originalTitle;
    private Integer runtime;
    private String youTubeTrailerKey01;

    private List<Genre> genres = null;
    private String url01;


    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getYouTubeTrailerKey01() {
        return youTubeTrailerKey01;
    }

    public void setYouTubeTrailerKey01(String youTubeTrailerKey01) {
        this.youTubeTrailerKey01 = youTubeTrailerKey01;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getUrl01() {
        return url01;
    }

    public void setUrl01(String url01) {
        this.url01 = url01;
    }


}

