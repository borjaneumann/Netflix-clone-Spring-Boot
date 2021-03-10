package com.netflixApp.client;

import com.netflixApp.controller.MovieController;
import com.netflixApp.dto.fanArt.FanArtInfo;
import com.netflixApp.dto.fanArt.Hdmovielogo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({MockitoExtension.class})
class MovieFanArtTest2 {
    @Mock
    private MovieFanArt movieFanArt;
    @InjectMocks
    private MovieController movieController;
    @Value("${fanArt.api_key}")
    private String fanArt_api_key;

    MovieFanArtTest2() {
    }

    @Test
    void getFanArtDetails() {

        FanArtInfo fanArtInfo = new FanArtInfo();
        List<Hdmovielogo> hdMovieLogosList = new ArrayList<>();
        Hdmovielogo hdmovielogoObj = new Hdmovielogo();

//        fanArtInfo.setName("Ad Astra");
        fanArtInfo.setTmdbId("550");
        hdmovielogoObj.setUrl("https://assets.fanart.tv");
        hdmovielogoObj.setId("1");

        hdMovieLogosList.add(hdmovielogoObj);

        fanArtInfo.setHdmovielogo(hdMovieLogosList);

        Mockito.when(movieFanArt.getFanArtDetails(123, fanArt_api_key)).thenReturn(fanArtInfo);

        FanArtInfo result = movieController.getLogos(123);

        Assertions.assertEquals(fanArtInfo.getName(), result.getName());
        Assertions.assertEquals(fanArtInfo.getTmdbId(), result.getTmdbId());

        Assertions.assertEquals(fanArtInfo.getHdmovielogo().get(0).getUrl(), result.getHdmovielogo().get(0).getUrl());
        Assertions.assertEquals(fanArtInfo.getHdmovielogo().get(0).getId(), result.getHdmovielogo().get(0).getId());
    }
}

