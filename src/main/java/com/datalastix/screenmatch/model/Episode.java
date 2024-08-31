package com.datalastix.screenmatch.model;

import java.time.LocalDate;

public class Episode {
  //attributes
  private Integer seasonNumber;
  private String  title;
  private Integer episodeNumber;
  private Double ratings;
  private LocalDate releaseDate;

  public Episode(Integer seasonNumber, DataEpisode dataEpisode) {
      this.seasonNumber = seasonNumber;
      this.title = dataEpisode.title();
      this.episodeNumber = dataEpisode.episode();
      this.ratings = Double.valueOf(dataEpisode.ratings());
      this.releaseDate = LocalDate.parse(dataEpisode.releaseDate());
  }

  public Integer getSeasonNumber() {
      return seasonNumber;
  }

  public void setSeasonNumber(Integer season) {
      this.seasonNumber = season;
  }

  public String getTitle() {
      return title;
  }

  public void setTitle(String title) {
      this.title = title;
  }

  public Integer getEpisodeNumber() {
      return episodeNumber;
  }

  public void setEpisodeNumber(Integer episodeNumber) {
      this.episodeNumber = episodeNumber;
  }

  public Double getRatings() {
      return ratings;
  }

  public void setRatings(Double ratings) {
      this.ratings = ratings;
  }

  public LocalDate getReleaseDate() {
      return releaseDate;
  }

  public void setReleaseDate(LocalDate releaseDate) {
      this.releaseDate = releaseDate;
  }
}
