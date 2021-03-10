package com.netflixApp.service;

import com.netflixApp.client.MovieFanArt;
import com.netflixApp.client.MovieTMDB;
import com.netflixApp.dto.actor.ActorDiscoverDto;
import com.netflixApp.dto.disneyDiscover.DisneyDiscoverDto;
import com.netflixApp.dto.fanArt.FanArtInfo;
import com.netflixApp.dto.filmDetailKaart.FilmDetails;
import com.netflixApp.dto.video.TrailerInfo;
import com.netflixApp.view.ActorMoviesView;
import com.netflixApp.view.DisneyMoviesView;
import com.netflixApp.view.YearsDetailsList2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActorMoviesService {
    public static final String POPULARITY_DESC = "popularity.desc";
    @Autowired
    private MovieTMDB movieTMDB;
    @Autowired
    private MovieFanArt movieFanArt;
    @Autowired
    private FilmDetailKaartService filmDetailKaartService;

    @Value("${tmdb.api_key}")
    private String api_key;
    private String lang ="en-US";

    @Value("${fanArt.api_key}")
    private String fanArt_api_key;

    public ActorMoviesView getActorMovies(String castNumber){

        ActorMoviesView actorMoviesView= new ActorMoviesView();

        ActorDiscoverDto actorDiscoverDto= movieTMDB.getActorSearch(api_key, lang, "popularity.desc",1, castNumber);
        ActorDiscoverDto nextActorDiscoverDto= movieTMDB.getActorSearch(api_key, lang, "popularity.desc",2, castNumber);


        List<YearsDetailsList2> yearsDetailsList2 = new ArrayList<>();

        for (int i = 0; i < actorDiscoverDto.getResults().size(); i++) {


            YearsDetailsList2 eightiesDetailsListObj = new YearsDetailsList2();

            eightiesDetailsListObj.setOverview(actorDiscoverDto.getResults().get(i).getOverview());
            eightiesDetailsListObj.setBackdropPath(actorDiscoverDto.getResults().get(i).getBackdropPath());
            eightiesDetailsListObj.setOriginalTitle(actorDiscoverDto.getResults().get(i).getOriginalTitle());
            eightiesDetailsListObj.setId(actorDiscoverDto.getResults().get(i).getId());

            FilmDetails FilmDetailsDto = movieTMDB.getFilmDetails(actorDiscoverDto.getResults().get(i).getId(), api_key,
                    lang,
                    "videos",
                    "credits");
            eightiesDetailsListObj.setGenres(FilmDetailsDto.getGenres());
            eightiesDetailsListObj.setRuntime(FilmDetailsDto.getRuntime());

            //logos
            FanArtInfo fanArtDto = movieFanArt.getFanArtDetails(actorDiscoverDto.getResults().get(i).getId(),
                    fanArt_api_key);

            if ((fanArtDto.getMovielogo() == null || fanArtDto.getMovielogo().size() == 0) && (fanArtDto.getHdmovielogo() == null || fanArtDto.getHdmovielogo().size() == 0)) {
                eightiesDetailsListObj.setUrl01("");

            } else if (fanArtDto.getHdmovielogo() != null || fanArtDto.getHdmovielogo().size() != 0){
                eightiesDetailsListObj.setUrl01(fanArtDto.getHdmovielogo().get(0).getUrl());
            } else{
                eightiesDetailsListObj.setUrl01(fanArtDto.getMovielogo().get(0).getUrl());}


            //Trailers

            TrailerInfo trailerInfoDto = movieTMDB.getVideosById(actorDiscoverDto.getResults().get(i).getId(), api_key,
                    lang);
            if (trailerInfoDto == null || trailerInfoDto.getResults().size() == 0 ) {
                eightiesDetailsListObj.setYouTubeTrailerKey01("Trailer not available");
            } else {
                eightiesDetailsListObj.setYouTubeTrailerKey01(trailerInfoDto.getResults().get(0).getKey());

            }

            yearsDetailsList2.add(eightiesDetailsListObj);

        }

        for (int i = 0; i <nextActorDiscoverDto.getResults().size(); i++) {

            YearsDetailsList2 eightiesDetailsListObj1 = new YearsDetailsList2();

            eightiesDetailsListObj1.setOverview(nextActorDiscoverDto.getResults().get(i).getOverview());
            eightiesDetailsListObj1.setBackdropPath(nextActorDiscoverDto.getResults().get(i).getBackdropPath());
            eightiesDetailsListObj1.setOriginalTitle(nextActorDiscoverDto.getResults().get(i).getOriginalTitle());
            eightiesDetailsListObj1.setId(nextActorDiscoverDto.getResults().get(i).getId());

            FilmDetails FilmDetailsDto = movieTMDB.getFilmDetails(nextActorDiscoverDto.getResults().get(i).getId(), api_key,lang,
                    "videos",
                    "credits" );
            eightiesDetailsListObj1.setGenres(FilmDetailsDto.getGenres());
            eightiesDetailsListObj1.setRuntime(FilmDetailsDto.getRuntime());
//
//
            //logos
            FanArtInfo fanArtDto = movieFanArt.getFanArtDetails(nextActorDiscoverDto.getResults().get(i).getId(),
                    fanArt_api_key);

            if ((fanArtDto.getMovielogo() == null || fanArtDto.getMovielogo().size() == 0) && (fanArtDto.getHdmovielogo() == null || fanArtDto.getHdmovielogo().size() == 0)) {
                eightiesDetailsListObj1.setUrl01("");

            } else if (fanArtDto.getHdmovielogo() != null || fanArtDto.getHdmovielogo().size() != 0){
                eightiesDetailsListObj1.setUrl01(fanArtDto.getHdmovielogo().get(0).getUrl());
            } else{
                eightiesDetailsListObj1.setUrl01(fanArtDto.getMovielogo().get(0).getUrl());}
//
//
//
//            //Trailers
            TrailerInfo trailerInfoDto = movieTMDB.getVideosById(nextActorDiscoverDto.getResults().get(i).getId(), api_key,
                    lang);
            if (trailerInfoDto == null || trailerInfoDto.getResults().size() == 0 ) {
                eightiesDetailsListObj1.setYouTubeTrailerKey01("Trailer not available");
            } else {
                eightiesDetailsListObj1.setYouTubeTrailerKey01(trailerInfoDto.getResults().get(0).getKey());

            }
            yearsDetailsList2.add(eightiesDetailsListObj1);

        }
        actorMoviesView.setActorsMoviesList(yearsDetailsList2);

        return actorMoviesView;
    }


}
