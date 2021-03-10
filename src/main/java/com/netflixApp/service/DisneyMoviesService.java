package com.netflixApp.service;

import com.netflixApp.client.MovieFanArt;
import com.netflixApp.client.MovieTMDB;
import com.netflixApp.dto.disneyDiscover.DisneyDiscoverDto;
import com.netflixApp.dto.fanArt.FanArtInfo;
import com.netflixApp.dto.filmDetailKaart.FilmDetails;
import com.netflixApp.dto.video.TrailerInfo;
import com.netflixApp.view.DisneyMoviesView;
import com.netflixApp.view.YearsDetailsList;
import com.netflixApp.view.YearsDetailsList2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DisneyMoviesService {
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

    public DisneyMoviesView getDisneyMovies(String companyNumber){

        DisneyMoviesView disneyMoviesView= new DisneyMoviesView();

        DisneyDiscoverDto disneyDiscoverDto= movieTMDB.getDisneyMovies(api_key, lang,POPULARITY_DESC,1,
                companyNumber);
        DisneyDiscoverDto nextDisneyDiscoverDto= movieTMDB.getDisneyMovies(api_key, lang,POPULARITY_DESC,2,
                companyNumber);

        List<YearsDetailsList2> yearsDetailsList = assembleListOfYearDetailList(disneyDiscoverDto);
        List<YearsDetailsList2> yearsDetailsList2 = assembleListOfYearDetailList(nextDisneyDiscoverDto);

        yearsDetailsList2.addAll(yearsDetailsList);
        disneyMoviesView.setDisneyMoviesList(yearsDetailsList2);

        return disneyMoviesView;
    }

    private List<YearsDetailsList2> assembleListOfYearDetailList(DisneyDiscoverDto disneyDiscoverDto) {
        List<YearsDetailsList2> yearsDetailsList2 = new ArrayList<>();

        for (int i = 0; i < disneyDiscoverDto.getResults().size(); i++) {


            YearsDetailsList2 eightiesDetailsListObj = new YearsDetailsList2();

            eightiesDetailsListObj.setOverview(disneyDiscoverDto.getResults().get(i).getOverview());
            eightiesDetailsListObj.setBackdropPath(disneyDiscoverDto.getResults().get(i).getBackdropPath());
            eightiesDetailsListObj.setOriginalTitle(disneyDiscoverDto.getResults().get(i).getOriginalTitle());
            eightiesDetailsListObj.setId(disneyDiscoverDto.getResults().get(i).getId());

            FilmDetails FilmDetailsDto = movieTMDB.getFilmDetails(disneyDiscoverDto.getResults().get(i).getId(), api_key,
                    lang,
                    "videos",
                    "credits");
            eightiesDetailsListObj.setGenres(FilmDetailsDto.getGenres());
            eightiesDetailsListObj.setRuntime(FilmDetailsDto.getRuntime());

            //logos
            FanArtInfo fanArtDto = movieFanArt.getFanArtDetails(disneyDiscoverDto.getResults().get(i).getId(),
                    fanArt_api_key);

            if (fanArtDto== null || fanArtDto.getMovielogo() == null || fanArtDto.getMovielogo().size() == 0 || fanArtDto.getHdmovielogo() == null || fanArtDto.getHdmovielogo().size() == 0) {
                eightiesDetailsListObj.setUrl01("");
            }else{
                if(fanArtDto.getHdmovielogo()!=null){
                    eightiesDetailsListObj.setUrl01(fanArtDto.getHdmovielogo().get(0).getUrl());
                }else{
                    eightiesDetailsListObj.setUrl01(fanArtDto.getMovielogo().get(0).getUrl());
                }
            }


            //Trailers

            TrailerInfo trailerInfoDto = movieTMDB.getVideosById(disneyDiscoverDto.getResults().get(i).getId(), api_key,
                    lang);
            if (trailerInfoDto == null || trailerInfoDto.getResults().size() == 0 ) {
                eightiesDetailsListObj.setYouTubeTrailerKey01("Trailer not available");
            } else {
                eightiesDetailsListObj.setYouTubeTrailerKey01(trailerInfoDto.getResults().get(0).getKey());

            }

            yearsDetailsList2.add(eightiesDetailsListObj);

        }
        return yearsDetailsList2;
    }


}
