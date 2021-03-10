package com.netflixApp.service;

import com.netflixApp.client.MovieFanArt;
import com.netflixApp.client.MovieTMDB;
import com.netflixApp.dto.discoverPerYear.PageInfo;
import com.netflixApp.dto.discoverPerYear.ResultsPerYear;
import com.netflixApp.dto.fanArt.FanArtInfo;
import com.netflixApp.dto.filmDetailKaart.FilmDetails;
import com.netflixApp.dto.video.TrailerInfo;
import com.netflixApp.view.YearsDetailsList;
import com.netflixApp.view.YearsDetailsList2;
import com.netflixApp.view.YearsLaneView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class YearsLaneService {
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

    public YearsLaneView getMoviesPerYear(String release_date_gte, String release_date_lte){

        YearsLaneView yearsLaneView = new YearsLaneView();
        PageInfo pageInfoDto= movieTMDB.getMoviePerYear(api_key,lang, POPULARITY_DESC,1, release_date_gte, release_date_lte);

        PageInfo nextPageInfoDto = movieTMDB.getMoviePerYear(api_key,lang,POPULARITY_DESC,2,
                release_date_gte,
                release_date_lte);

        List<YearsDetailsList> yearsDetailsList1 = assemblyListOfYearsDetailList(pageInfoDto);
        List<YearsDetailsList> yearsDetailsList = assemblyListOfYearsDetailList(nextPageInfoDto);

        yearsDetailsList.addAll(yearsDetailsList1);
        yearsLaneView.setYearsDetailsList(yearsDetailsList);

        return yearsLaneView;
    }

    private List<YearsDetailsList> assemblyListOfYearsDetailList(PageInfo pageInfoDto) {
        List<YearsDetailsList> yearsDetailsList = new ArrayList<>();

        for (int i = 0; i < pageInfoDto.getResults().size(); i++) {


            YearsDetailsList eightiesDetailsListObj = new YearsDetailsList();

            eightiesDetailsListObj.setOverview(pageInfoDto.getResults().get(i).getOverview());
            eightiesDetailsListObj.setBackdropPath(pageInfoDto.getResults().get(i).getBackdropPath());
            eightiesDetailsListObj.setOriginalTitle(pageInfoDto.getResults().get(i).getOriginalTitle());
            eightiesDetailsListObj.setId(pageInfoDto.getResults().get(i).getId());

            FilmDetails FilmDetailsDto = movieTMDB.getFilmDetails(pageInfoDto.getResults().get(i).getId(), api_key,
                    lang,
                    "videos",
                    "credits");
            eightiesDetailsListObj.setGenres(FilmDetailsDto.getGenres());
            eightiesDetailsListObj.setRuntime(FilmDetailsDto.getRuntime());

            //logos
            FanArtInfo fanArtDto = movieFanArt.getFanArtDetails(pageInfoDto.getResults().get(i).getId(),
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

            TrailerInfo trailerInfoDto = movieTMDB.getVideosById(pageInfoDto.getResults().get(i).getId(), api_key,
                    lang);
            if (trailerInfoDto == null || trailerInfoDto.getResults().size() == 0 ) {
                eightiesDetailsListObj.setYouTubeTrailerKey01("Trailer not available");
            } else {
                eightiesDetailsListObj.setYouTubeTrailerKey01(trailerInfoDto.getResults().get(0).getKey());

            }

            yearsDetailsList.add(eightiesDetailsListObj);

        }
        return yearsDetailsList;
    }


}
