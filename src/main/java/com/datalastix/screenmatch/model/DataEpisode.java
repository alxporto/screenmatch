package com.datalastix.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataEpisodes(@JsonAlias("Title") String title,
                           @JsonAlias("Episode") Integer episode,
                           @JsonAlias("imdbRating") String ratings,
                           @JsonAlias("ReleaseDate") String releaseDate) {
}
