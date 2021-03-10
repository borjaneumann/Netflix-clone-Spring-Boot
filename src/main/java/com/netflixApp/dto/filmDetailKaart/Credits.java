
package com.netflixApp.dto.filmDetailKaart;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "cast",
    "crew"
})
public class Credits {

    @JsonProperty("cast")
    private List<Cast> cast = null;
    @JsonProperty("crew")
    private List<Crew> crew = null;

    @JsonProperty("cast")
    public List<Cast> getCast() {
        return cast;
    }

    @JsonProperty("cast")
    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    @JsonProperty("crew")
    public List<Crew> getCrew() {
        return crew;
    }

    @JsonProperty("crew")
    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }

}
