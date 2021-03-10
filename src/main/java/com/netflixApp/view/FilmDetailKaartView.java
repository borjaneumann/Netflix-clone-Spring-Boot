package com.netflixApp.view;
import com.netflixApp.dto.filmDetailKaart.Genre;

import java.util.List;

public class FilmDetailKaartView {

    //General card details
    private String overview;
    private Integer id;
    private Integer runtime;
    private String originalTitle;
    private List<Genre> genres = null;
    private String backdropPath;
    private String youTubeTrailerKey01;
    private String youTubeTrailerKey02;


    //CREW
    private String DirectorName;
    private List<Actors> actors;

    //LOGOS
    private String name;
    private String tmdbId;
    private String imdbId;
    private String url01;
    private String url02;

    //-----------------getters and setters---------------------//

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

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getYouTubeTrailerKey01() {
        return youTubeTrailerKey01;
    }

    public void setYouTubeTrailerKey01(String youTubeTrailerKey01) {
        this.youTubeTrailerKey01 = youTubeTrailerKey01;
    }

    public String getYouTubeTrailerKey02() {
        return youTubeTrailerKey02;
    }

    public void setYouTubeTrailerKey02(String youTubeTrailerKey02) {
        this.youTubeTrailerKey02 = youTubeTrailerKey02;
    }

    public String getDirectorName() {
        return DirectorName;
    }

    public void setDirectorName(String directorName) {
        DirectorName = directorName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl01() {
        return url01;
    }

    public void setUrl01(String url01) {
        this.url01 = url01;
    }

    public String getUrl02() {
        return url02;
    }

    public void setUrl02(String url02) {
        this.url02 = url02;
    }

    public String getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(String tmdbId) {
        this.tmdbId = tmdbId;
    }

    public String getImdbId() { return imdbId;}

    public void setImdbId(String imdbId) { this.imdbId = imdbId; }

    public List<Actors> getActors() {
        return actors;
    }
    public void setActors(List<Actors> actors) {
        this.actors = actors;
    }

}
