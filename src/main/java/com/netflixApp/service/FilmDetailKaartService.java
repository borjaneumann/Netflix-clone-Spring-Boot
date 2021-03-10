package com.netflixApp.service;

import com.netflixApp.client.MovieFanArt;
import com.netflixApp.client.MovieTMDB;
import com.netflixApp.dto.credits.Cast;
import com.netflixApp.dto.credits.CreditsInfo;
import com.netflixApp.dto.fanArt.FanArtInfo;
import com.netflixApp.dto.filmDetailKaart.Credits;
import com.netflixApp.dto.filmDetailKaart.Crew;
import com.netflixApp.dto.filmDetailKaart.FilmDetails;
import com.netflixApp.dto.video.Result;
import com.netflixApp.dto.video.TrailerInfo;
import com.netflixApp.view.*;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmDetailKaartService {
    @Autowired
    private MovieTMDB movieTMDB;
    @Autowired
    private MovieFanArt movieFanArt;

    @Value("${tmdb.api_key}")
    private String api_key;
    private String lang = "en-US";

    @Value("${fanArt.api_key}")
    private String fanArt_api_key;

    public FilmDetailKaartView getFilmDetailKaart(int movieID) {

        FilmDetailKaartView dtoFilmDetailView = new FilmDetailKaartView();

        FilmDetails FilmDetailsDto = movieTMDB.getFilmDetails(movieID, api_key, lang, "videos", "credits");
        TrailerInfo trailerInfo = movieTMDB.getVideosById(movieID, api_key, lang);
        CreditsInfo creditDto = movieTMDB.getCredits(movieID, api_key, lang);
        FanArtInfo fanArtDto = movieFanArt.getFanArtDetails(movieID, fanArt_api_key);

        //GETTING AND SETTING
        dtoFilmDetailView.setOriginalTitle(FilmDetailsDto.getOriginalTitle());
        dtoFilmDetailView.setOverview(FilmDetailsDto.getOverview());
        dtoFilmDetailView.setBackdropPath(FilmDetailsDto.getBackdropPath());
        dtoFilmDetailView.setId(FilmDetailsDto.getId());
        dtoFilmDetailView.setGenres(FilmDetailsDto.getGenres());
        dtoFilmDetailView.setRuntime(FilmDetailsDto.getRuntime());

        //--------------trailers------------------//
        dtoFilmDetailView.setYouTubeTrailerKey01(trailerInfo.getResults().get(0).getKey());
        dtoFilmDetailView.setYouTubeTrailerKey02(trailerInfo.getResults().get(1).getKey());

        //=======================director name-----------------------//

        for (int i = 0; i < creditDto.getCrew().size(); i++) {
            if (creditDto.getCrew().get(i).getJob().contentEquals("Director")) {
                dtoFilmDetailView.setDirectorName(creditDto.getCrew().get(i).getName());
            }

        }
        //------------------------logos-------------------------------//
        System.out.println(fanArtDto);

        if (fanArtDto.getHdmovielogo() == null || fanArtDto.getHdmovielogo().size() == 0) {
            dtoFilmDetailView.setUrl01(fanArtDto.getMovielogo().get(0).getUrl());
            dtoFilmDetailView.setUrl02(fanArtDto.getMovielogo().get(1).getUrl());

        } else {
            dtoFilmDetailView.setUrl01(fanArtDto.getHdmovielogo().get(0).getUrl());
            dtoFilmDetailView.setUrl02(fanArtDto.getHdmovielogo().get(1).getUrl());
        }

        dtoFilmDetailView.setTmdbId(fanArtDto.getTmdbId());
        dtoFilmDetailView.setImdbId(fanArtDto.getImdbId());
        dtoFilmDetailView.setName(fanArtDto.getName());


        //-----------------------Cast names---------------------------//
        List<Actors> actorsList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Actors actorsObj = new Actors();
            actorsObj.setOriginalName(creditDto.getCast().get(i).getOriginalName());
            actorsList.add(actorsObj);

        }
        dtoFilmDetailView.setActors(actorsList);

        return dtoFilmDetailView;
    }

}