package com.netflixApp.service;

import com.netflixApp.client.MovieFanArt;
import com.netflixApp.client.MovieTMDB;
import com.netflixApp.dto.discoverPerYear.PageInfo;
import com.netflixApp.dto.search_personID.Actor;
import com.netflixApp.dto.search_personID.SearchPersonDto;
import com.netflixApp.view.ActorsSearchView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ActorsListInfo {
    public static final String POPULARITY_DESC = "popularity.desc";
    @Autowired
    private MovieTMDB movieTMDB;
    @Autowired
    private MovieFanArt movieFanArt;
    @Autowired
    private FilmDetailKaartService filmDetailKaartService;

    @Value("${tmdb.api_key}")
    private String api_key;
    private final String lang = "en-US";

    @Value("${fanArt.api_key}")
    private String fanArt_api_key;

    public List<Actor> getActorsPerSearch(String inputName) {
        List<Actor> actorList = new ArrayList<>();
        SearchPersonDto searchPersonDto = movieTMDB.getPersonSearchById(api_key, lang, inputName);
        ActorsSearchView actorsSearchView = new ActorsSearchView();


        for (int i = 0; i < searchPersonDto.getResults().size(); i++) {
            Actor actor = new Actor();
            int actorId = searchPersonDto.getResults().get(i).getId();
            actor.setId(actorId);
            actor.setActorName(searchPersonDto.getResults().get(i).getName());
            actor.setPopularity(searchPersonDto.getResults().get(i).getPopularity());

            PageInfo pageInfoDto = movieTMDB.getMoviesPerActor(api_key, lang, POPULARITY_DESC, 1,
                    searchPersonDto.getResults().get(i).getId().toString());

            actorList.add(actor);

        }
        actorList.sort(Collections.reverseOrder());
        return actorList;
    }
}
