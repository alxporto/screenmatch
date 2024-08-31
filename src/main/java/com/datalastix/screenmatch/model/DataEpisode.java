package com.datalastix.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataEpisode(@JsonAlias("Title") String title,
                          @JsonAlias("Episode") Integer episodeNumber,
                          @JsonAlias("imdbRating") String ratings,
                          @JsonAlias("ReleaseDate") String releaseDate) {
}
