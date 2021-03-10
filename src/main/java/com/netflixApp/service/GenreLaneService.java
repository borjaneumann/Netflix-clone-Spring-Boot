package com.netflixApp.service;

import com.netflixApp.client.MovieFanArt;
import com.netflixApp.client.MovieTMDB;
import com.netflixApp.dto.discoverPerYear.PageInfo;
import com.netflixApp.dto.fanArt.FanArtInfo;
import com.netflixApp.dto.filmDetailKaart.FilmDetails;
import com.netflixApp.dto.video.TrailerInfo;
import com.netflixApp.view.GenreLaneView;
import com.netflixApp.view.YearsDetailsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class GenreLaneService {
public static final String POPULARITY_DESC = "vote_count.desc";

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

    public GenreLaneView getMoviesPerGenreService(String genredID){

        GenreLaneView genreLaneView = new GenreLaneView();
        PageInfo pageInfoDto= movieTMDB.getMoviesPerGenre(api_key,lang,POPULARITY_DESC,1, genredID);
        PageInfo nextPageInfoDto= movieTMDB.getMoviesPerGenre(api_key,lang, POPULARITY_DESC,2, genredID);

        List<YearsDetailsList> yearsDetailsList = assemblyListOfYearsDetailList(pageInfoDto);
        List<YearsDetailsList> yearsDetailsListNext = assemblyListOfYearsDetailList(nextPageInfoDto);
        yearsDetailsList.addAll(yearsDetailsListNext);
        genreLaneView.setGenreLaneList(yearsDetailsList);

        return genreLaneView;
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
