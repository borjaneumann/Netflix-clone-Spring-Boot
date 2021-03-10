package com.netflixApp.client;


import com.netflixApp.dto.actor.ActorDiscoverDto;
import com.netflixApp.dto.credits.CreditsInfo;
import com.netflixApp.dto.disneyDiscover.DisneyDiscoverDto;
import com.netflixApp.dto.filmDetailKaart.FilmDetails;
import com.netflixApp.dto.movieID.Genre;
import com.netflixApp.dto.movieID.MovieDetailsResponse;
import com.netflixApp.dto.discoverPerYear.PageInfo;
import com.netflixApp.dto.images.MovieImagesDto;
import com.netflixApp.dto.person_ID.ActorPerIdDto;
import com.netflixApp.dto.search.SearchDto;
import com.netflixApp.dto.search_personID.SearchPersonDto;
import com.netflixApp.dto.video.TrailerInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url="https://api.themoviedb.org/3/", name="TMDB-API")
public interface MovieTMDB {

    //---------------------------------GETTING FILM CARD DETAILS-------------------------------------//
    //https://api.themoviedb.org/3/movie/550?api_key=fc4000ab8d22a3a3e78d15c07944381c&language=en-US&append_to_response=videos,credits
    @GetMapping("/movie/{movieID}")
    FilmDetails getFilmDetails(@RequestParam("movieID") int movieID,
                               @RequestParam(value="api_key") String api_key,
                               @RequestParam(value="language") String language,
                               @RequestParam(value="append_to_response") String appendVideos,
                               @RequestParam(value="append_to_response") String appendCredits) ;




    //--------------------GET MOVIES PER YEAR (DISCOVER PAGE) (only page 1 and 20 entries)------------//
    //https://api.themoviedb.org/3/discover/movie?api_key=fc4000ab8d22a3a3e78d15c07944381c&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=true&page=1&release_date.gte=1990-01-01&release_date.lte=2000-01-01
    @GetMapping("discover/movie")
    PageInfo getMoviePerYear(@RequestParam(value="api_key") String api_key,
                             @RequestParam(value="language") String language,
                             @RequestParam(value="sort_by")String sort_by,
                             @RequestParam(value="page")int page,
                             @RequestParam(value="release_date.gte")String release_date_gte,
                             @RequestParam(value="release_date.lte")String release_date_lte);


    //GETTING DISNEY MOVIES (DISCOVER PAGE) (only page 1 and 20 entries)
    //https://api.themoviedb.org/3/discover/movie?api_key=fc4000ab8d22a3a3e78d15c07944381c&language=en-US&sort_by=popularity.desc&include_adult=false&page=1&with_companies=2
    @GetMapping("discover/movie")
    DisneyDiscoverDto getDisneyMovies(@RequestParam(value="api_key") String api_key,
                                      @RequestParam(value="language") String language,
                                      @RequestParam(value="sort_by")String sort_by,
                                      @RequestParam("page") int page,
                                      @RequestParam(value = "with_companies") String with_companies);


    //GETTING MOVIES PER ACTOR/ACTRESS (BRAD PITT ID:281)
    //https://api.themoviedb.org/3/discover/movie?api_key=fc4000ab8d22a3a3e78d15c07944381c&language=en-US&sort_by
    // =popularity.desc&page=1&with_cast=287
    //From Brad PItt there are 5 pages available.
    @GetMapping("discover/movie")
    ActorDiscoverDto getActorSearch (@RequestParam(value="api_key") String api_key,
                                     @RequestParam(value="language") String language,
                                     @RequestParam(value="sort_by")String sort_by,
                                     @RequestParam("page") int page,
                                     @RequestParam("with_cast") String with_cast);


    //---------------------------------------GETTING MOVIES PER GENRE-----------------------------------------------//

    ////https://api.themoviedb.org/3/movie/latest?api_key=fc4000ab8d22a3a3e78d15c07944381c&language=en-US
    //GETTING GENRE LIST
    @GetMapping("/genre/movie/list?api_key=fc4000ab8d22a3a3e78d15c07944381c&language=en-US")
    Genre getGenreList( ) ;

    //GETTING MOVIES BY GENRE (FILMS PAGE)
    //https://api.themoviedb.org/3/discover/movie?api_key=fc4000ab8d22a3a3e78d15c07944381c&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_genres=35
    @GetMapping("discover/movie")
    PageInfo getMoviesPerGenre(@RequestParam(value="api_key") String api_key,
                               @RequestParam(value="language") String language,
                               @RequestParam(value="sort_by")String sort_by,
                               @RequestParam(value="page")int page,
                               @RequestParam(value="with_genres")String genresID);

    //GETTING ACTORS MOVIES BY SEARCH
//    https://api.themoviedb.org/3/discover/movie?api_key=fc4000ab8d22a3a3e78d15c07944381c&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_cast=1461
    @GetMapping("discover/movie")
    PageInfo getMoviesPerActor(@RequestParam(value="api_key") String api_key,
                               @RequestParam(value="language") String language,
                               @RequestParam(value="sort_by")String sort_by,
                               @RequestParam(value="page")int page,
                               @RequestParam("with_cast")String castID);

    //GETTING ACTORS MOVIES BY SEARCH
//    https://api.themoviedb.org/3/discover/movie?api_key=fc4000ab8d22a3a3e78d15c07944381c&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_cast=1461
    @GetMapping("discover/movie")
    PageInfo getMoviesPerActorSearch(@RequestParam(value="api_key") String api_key,
                               @RequestParam(value="language") String language,
                               @RequestParam(value="sort_by")String sort_by,
                               @RequestParam(value="page")int page);


    //------------------------GETTING MOVIES BY ANY SEARCH-------------------------------------------///
    //https://api.themoviedb.org/3/search/multi?api_key=fc4000ab8d22a3a3e78d15c07944381c&language=en-US&query=brad
    // %20pitt&page=1&include_adult=false
    @GetMapping("search/multi")
    SearchDto getMultiSearch (@RequestParam(value="api_key") String api_key,
                              @RequestParam(value="language") String language,
                              @RequestParam("query") String searchQuery);




    //------------------------------------TESTS----------------------------------------------//
    //GETTING LATEST MOVIE
    //https://api.themoviedb.org/3/movie/latest?api_key=fc4000ab8d22a3a3e78d15c07944381c&language=en-US
    @GetMapping("movie/latest?api_key=fc4000ab8d22a3a3e78d15c07944381c&language&language=en-US")
    MovieDetailsResponse getLatestMovies() ;

    //GETTING SINGLE MOVIE BY ID
    // https://api.themoviedb.org/3/movie/550?api_key=fc4000ab8d22a3a3e78d15c07944381c&language=en-US
    @GetMapping("/movie/{movieID}")
    MovieDetailsResponse getMovieDetails(@RequestParam("movieID") int movieID,
                                  @RequestParam(value="api_key") String api_key,
                                  @RequestParam(value="language") String language) ;

    //GETTING MOVIE IMAGE BY ID
    //https://api.themoviedb.org/3/movie/550/images?api_key=fc4000ab8d22a3a3e78d15c07944381c&language=en-US
    @GetMapping("/movie/{movieID}/images")
    MovieImagesDto getMoviePics(@RequestParam("movieID") int movieID,
                                @RequestParam(value="api_key") String api_key);

//    //GETTING CREDITS BY ID (works)
//    //https://api.themoviedb.org/3/movie/550/credits?api_key=fc4000ab8d22a3a3e78d15c07944381c&language=en-US
//    @GetMapping("/movie/{movieID}/credits")
//    CreditsInfo getCredits(@RequestParam("movieID") String movieID,
//                    @RequestParam(value="api_key") String api_key,
//                    @RequestParam(value="language") String language);


    //GETTING CREDITS BY ID(VIEW)
    //https://api.themoviedb.org/3/movie/550/credits?api_key=fc4000ab8d22a3a3e78d15c07944381c&language=en-US
    @GetMapping("/movie/{movieID}/credits")
    CreditsInfo getCredits(@RequestParam("movieID") int movieID,
                           @RequestParam(value="api_key") String api_key,
                           @RequestParam(value="language") String language);


    //GETTING VIDEOS BY ID
    //https://api.themoviedb.org/3/movie/550/videos?api_key=fc4000ab8d22a3a3e78d15c07944381c&language=en-US
    @GetMapping("/movie/{movieID}/videos")
    TrailerInfo getVideosById(@RequestParam("movieID") int movieID,
                              @RequestParam(value="api_key") String api_key,
                              @RequestParam(value="language") String language);


    //GETTING SEARCH PER PERSON ID//PERSON ENDPOINT
    //https://api.themoviedb.org/3/person/287?api_key=fc4000ab8d22a3a3e78d15c07944381c&language=en-US
    @GetMapping("/person/{person_id}")
    ActorPerIdDto getPersonById(@RequestParam(value="api_key") String api_key,
                                @RequestParam(value="language") String language,
                                @RequestParam(value="person_id") int person_id);


    //GETTING SEARCH PER PERSON ID//SEARCH ENDPOINT
    //https://api.themoviedb.org/3/search/person?api_key=fc4000ab8d22a3a3e78d15c07944381c&language=en-US&query=Brad%20Pitt&page=1&include_adult=false
    @GetMapping("/search/person")
    SearchPersonDto getPersonSearchById(
                                @RequestParam(value="api_key") String api_key,
                                @RequestParam(value="language") String language,
                                @RequestParam(value="query") String query);

}


