package com.netflixApp.controller;

import com.netflixApp.client.MovieFanArt;
import com.netflixApp.client.MovieTMDB;
import com.netflixApp.dto.actor.ActorDiscoverDto;
import com.netflixApp.dto.credits.CreditsInfo;
import com.netflixApp.dto.disneyDiscover.DisneyDiscoverDto;
import com.netflixApp.dto.fanArt.FanArtInfo;
import com.netflixApp.dto.movieID.Genre;
import com.netflixApp.dto.movieID.MovieDetailsResponse;
import com.netflixApp.dto.discoverPerYear.PageInfo;
import com.netflixApp.dto.images.MovieImagesDto;
import com.netflixApp.dto.search.SearchDto;
import com.netflixApp.dto.search_personID.Actor;
import com.netflixApp.dto.video.TrailerInfo;
import com.netflixApp.service.*;
import com.netflixApp.view.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000" )
public class MovieController {

    private static final String POPULARITY_DESC =null ;
    @Autowired
    private MovieTMDB movieTMDB;
    private String lang ="en-US";

    @Value("${tmdb.api_key}")
    private String api_key;

    @Autowired
    private FilmDetailKaartService filmDetailKaartService;

    @Autowired
    private YearsLaneService yearsLaneService;

    @Autowired
    private DisneyMoviesService disneyMoviesService;

    @Autowired
    private ActorMoviesService actorMoviesService;

    @Autowired
    private GenreLaneService genreLaneService;

    @Autowired
    private ActorsListInfo actorsSearchService;

//-----------------------------------FanAart methods--------------------------------------//

    @Autowired
    private MovieFanArt movieFanArt;

    @Value("${fanArt.api_key}")
    private String fanArt_api_key;

    @GetMapping("/movie/logos/{movieFAID}")
    public FanArtInfo getLogos(@PathVariable int movieFAID){
        return movieFanArt.getFanArtDetails(movieFAID,fanArt_api_key);}
        

//-----------------------------------TMDB API methods--------------------------------------//

//-----------------------------Movie Detail Card Method-------------------------------//

    @GetMapping("/movie/{movieID}")
    public FilmDetailKaartView getFilmDetailKaart(@PathVariable int movieID){
        return filmDetailKaartService.getFilmDetailKaart(movieID);
    }

//-------------------------------Movies by Genre---FILMS PAGE--------------------------------------//

                                    //FILMS PAGE - COMEDIES//
    @GetMapping("/genre/comedy")
    public GenreLaneView getMoviesComedies(){
        return genreLaneService.getMoviesPerGenreService("35");
    }
                                    //FILMS PAGE - ACTION//
    @GetMapping("/genre/action")
    public GenreLaneView getMoviesAction(){
        return genreLaneService.getMoviesPerGenreService("28");}

                                    //FILMS PAGE - THRILLER//
    @GetMapping("/genre/thriller")
    public GenreLaneView getMoviesThriller(){
        return genreLaneService.getMoviesPerGenreService("53");}

                                    //FILMS PAGE - FAMILY//
    @GetMapping("/genre/family")
    public GenreLaneView getMoviesFamily(){
        return genreLaneService.getMoviesPerGenreService("10751");}

                                    //FILMS PAGE - FANTASY//
    @GetMapping("/genre/fantasy")
    public GenreLaneView getMoviesFantasy(){
        return genreLaneService.getMoviesPerGenreService("14");}

                                    //FILMS PAGE - CRIME//
    @GetMapping("/genre/crime")
    public GenreLaneView getMoviesCrimes(){
        return genreLaneService.getMoviesPerGenreService("80");}

                                    //FILMS PAGE - ADVENTURE//
    @GetMapping("/genre/adventure")
    public GenreLaneView getMoviesAdventure(){
        return genreLaneService.getMoviesPerGenreService("12");}

//----------------------------------DISCOVERY PAGE(per year/disney/actor_search)---------------------//

                                    //DISCOVERY - 80's movies//
    @GetMapping("/discover/80")
    public YearsLaneView getMovie80(){
        return yearsLaneService.getMoviesPerYear("1980-01-01","1990-01-01");
    }
                                    //DISCOVERY - 90's movies//
    @GetMapping("/discover/90")
    public YearsLaneView getMovie90(){
        return yearsLaneService.getMoviesPerYear("1990-01-01","2000-01-01");
    }
                                    //DISCOVERY - 00's movies//
    @GetMapping("/discover/00")
    public YearsLaneView getMovie00(){
        return yearsLaneService.getMoviesPerYear("2000-01-01","2010-01-01");
    }
                                    //DISCOVERY - Disney's movies//
    @GetMapping("discover/disney")
    public DisneyMoviesView getDisney(){
        return disneyMoviesService.getDisneyMovies( "2");
    }
                                    //DISCOVERY - Brad's movies//
    @GetMapping("discover/brad")
    public ActorMoviesView getBradMovies(){
        return actorMoviesService.getActorMovies( "287");
    }

    //-------------------------------Movies by Genre---FILMS PAGE--------------------------------------//

    //FILMS PAGE - COMEDIES//
    @GetMapping("/films/comedy/v1/{page}")
    public PageInfo getComedies(@PathVariable int page){
        return movieTMDB.getMoviesPerGenre(api_key,lang,"popularity.desc",page, "35");
    }

    //FILMS PAGE - ACTION//
    @GetMapping("/films/action/v1/{page}")
    public PageInfo getAction(@PathVariable int page) {
        return movieTMDB.getMoviesPerGenre(api_key,lang,"popularity.desc",page, "28");}

    //FILMS PAGE - THRILLER//
    @GetMapping("/films/thriller/v1/{page}")
    public PageInfo getThriller(@PathVariable int page){
        return movieTMDB.getMoviesPerGenre(api_key,lang,"popularity.desc",page, "53");}

    //FILMS PAGE - FAMILY//
    @GetMapping("/films/family/v1/{page}")
    public PageInfo getFamily(@PathVariable int page){
        return movieTMDB.getMoviesPerGenre(api_key,lang,"popularity.desc",page, "10751");}

    //FILMS PAGE - FANTASY//
    @GetMapping("/films/fantasy/v1/{page}")
    public PageInfo getFantasy(@PathVariable int page){
        return movieTMDB.getMoviesPerGenre(api_key,lang,"popularity.desc",page, "14");}

    //FILMS PAGE - CRIME//
    @GetMapping("/films/crime/v1/{page}")
    public PageInfo getCrime(@PathVariable int page){
        return movieTMDB.getMoviesPerGenre(api_key,lang,"popularity.desc",page, "80");}

    //FILMS PAGE - ADVENTURE//
    @GetMapping("/films/adventure/v1/{page}")
    public PageInfo getAdventure(@PathVariable int page){
        return movieTMDB.getMoviesPerGenre(api_key,lang,"popularity.desc",page, "12");}
    //--------------------------------------------------------------------------------------------------------

                                    //DISCOVERY - 90's movies//

    @GetMapping("/discover/90/v1/{page}")
    public PageInfo getMovie90(@PathVariable int page){
        return movieTMDB.getMoviePerYear(api_key,lang,"popularity.desc",page, "1990-01-01", "2000-01-01");
    }

                                    //DISCOVERY - 00's movies//

    @GetMapping("/discover/00/v1/{page}")
    public PageInfo getMovie00(@PathVariable int page){
        return movieTMDB.getMoviePerYear(api_key,lang,"popularity.desc",page, "2000-01-01", "2009-12-31");
    }

                                    //DISCOVERY - Disney movies//

    @GetMapping("discover/disney/v1/{page}")
    public DisneyDiscoverDto getDisneyDto(@PathVariable int page){
        return movieTMDB.getDisneyMovies(api_key, lang,"popularity.desc" ,page,"2");
    }

                                    //DISCOVERY - Actor lane//
    @GetMapping("discover/v1/actor/{page}")
    public ActorDiscoverDto getActor(@PathVariable int page){
        return movieTMDB.getActorSearch(api_key, lang, "popularity.desc",page, "287");
    }

                                //DISCOVERY SEARCH - Actors movies//

    @GetMapping("/search")
    public PageInfo getActor(@RequestParam String inputName){

        PageInfo pageInfoDto = movieTMDB.getMoviePerYear(api_key, lang, POPULARITY_DESC, 1,"1980-01-01" ,"2021-01-01" );
        SearchDto searchDto= movieTMDB.getMultiSearch(api_key, lang, inputName);
        for (int i = 0; i < searchDto.getResults().size(); i++) {
            if (inputName.equalsIgnoreCase(searchDto.getResults().get(i).getName() ) ) {
                searchDto.getResults().get(i).getId().toString();
            }
        }
        return movieTMDB.getMoviesPerActor(api_key,lang,"popularity.desc",
                1,searchDto.getResults().get(0).getId().toString());
    }

    //------these two endpoints can work together at the frontend to create a search engine---------------//
    @GetMapping("/actor/search")
    public List<Actor> getActorsView(@RequestParam String inputName){
        return actorsSearchService.getActorsPerSearch(inputName);
    }

    @GetMapping("movies/actor/{castID}")
    public ActorMoviesView getActorMoviesByIdTest(@PathVariable String castID){
        return actorMoviesService.getActorMovies(castID);
    }

    //------------------------------------------------------------------------------------------//

    //SEARCH - Multi Search
    @GetMapping("search/v1/{query}")
    public SearchDto getSearchDto(@PathVariable String query){
        return movieTMDB.getMultiSearch(api_key, lang, query);
    }

    //---------------------------------- practice methods----------------------------------//

    @GetMapping("/latest")
    public MovieDetailsResponse getLatest() {
        return movieTMDB.getLatestMovies();
    }

    @GetMapping("/genre")
    public Genre getGenre() {return movieTMDB.getGenreList();

    }
    //videos by id
    @GetMapping("/video/{movieID}")
    public TrailerInfo getVideos(@PathVariable int movieID){
        return movieTMDB.getVideosById(movieID, api_key, lang);
    }

    //videos by Movieid
    @GetMapping("/movie/pics/{movieID}")
    public MovieImagesDto getPics(@PathVariable int movieID){
        return movieTMDB.getMoviePics(movieID,api_key);
    }

    //credits by Movieid
    @GetMapping("/movie/credits/{movieID}")
    public CreditsInfo getCreditsByMovieId(@PathVariable int movieID){
        return movieTMDB.getCredits(movieID, api_key,lang);
    }
}


