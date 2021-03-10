package com.netflixApp.dto.search_personID;

import com.netflixApp.view.ActorsSearchView;
import com.netflixApp.view.YearsDetailsList;

import java.util.List;

public class Actor implements Comparable<Actor>{
    private Integer id;
    private String ActorName;
    private Float popularity;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActorName() {
        return ActorName;
    }

    public void setActorName(String actorName) {
        ActorName = actorName;
    }

    public Float getPopularity() {
        return popularity;
    }

    public void setPopularity(Float popularity) {
        this.popularity = popularity;
    }

    @Override
    public int compareTo(Actor o) {
        return this.getPopularity().compareTo(o.getPopularity());
    }
}
