package com.netflixApp.client;

import com.netflixApp.dto.fanArt.FanArtInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(url="http://webservice.fanart.tv/v3/", name="FANART-API", decode404 = true)
public interface MovieFanArt {

    //GETTING MOVIE LOGOS
    //example http://webservice.fanart.tv/v3/movies/id?api_key=6fa42b0ef3b5f3aab6a7edaa78675ac
    //example http://webservice.fanart.tv/v3/movies/550?api_key=6bc7cf94022a8007039c297484b4da7e
    @GetMapping("movies/{id}")
    FanArtInfo getFanArtDetails(@PathVariable("id") int id,
                              @RequestParam(value = "api_key") String api_key);
}