package com.netflixApp.client;

import com.netflixApp.controller.MovieController;
import com.netflixApp.dto.fanArt.FanArtInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RunAs;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieFanArtTest {
        @Mock
        private MovieFanArt movieFanArt;
        @InjectMocks
        private MovieController movieController;
        @Value("${fanArt.api_key}")
        private String fanArt_api_key;
    @Test
    void getFanArtDetails() {
//        MovieFanArt movieFanArt = mock(MovieFanArt.class);
//        MovieController movieController = mock(MovieController.class);
        FanArtInfo fanArtInfo = new FanArtInfo();
        fanArtInfo.setName("Avengers");
        when(movieFanArt.getFanArtDetails(123,fanArt_api_key)).thenReturn(fanArtInfo);
        FanArtInfo result= movieController.getLogos(123);
        assertEquals(fanArtInfo.getName(),result.getName());

        //MovieFanArt movieFanArt = mock(MovieFanArt.class);
//        MovieController movieController = mock(MovieController.class);
//        when(movieFanArt.getFanArtDetails("550","6bc7cf94022a8007039c297484b4da7e")).thenReturn(movieController.logito("550"));
    }
}



//“When passwordEncoder.encode(“1”) is called, return an a.”
//    when(passwordEncoder.encode("1")).thenReturn("a");


//    int expected=3;
//    int actual = mathUtils.add(1,2);
//    assertEquals(expected,actual);

//    @GetMapping("movies/{id}")
//    FanArtInfo getFanArtDetails(@PathVariable("id") String id,
//                                @RequestParam(value = "api_key") String api_key);