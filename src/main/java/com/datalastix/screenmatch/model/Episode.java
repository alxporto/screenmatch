package com.datalastix.screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Episode {
  //attributes
  private Integer seasonNumber;
  private String  title;
  private Integer episodeNumber;
  private Double  ratings;
  private LocalDate releaseDate;

  public Episode(Integer seasonNumber, DataEpisode dataEpisode) {
      this.seasonNumber = seasonNumber;
      this.title = dataEpisode.title();
      this.episodeNumber = dataEpisode.episodeNumber();

      try {
          this.ratings = Double.valueOf(dataEpisode.ratings());
      } catch (NumberFormatException ex) {
          this.ratings = 0.0;
      }

      try {
          this.releaseDate = LocalDate.parse(dataEpisode.releaseDate());
      } catch (DateTimeParseException ex) {
          this.releaseDate = null;
      }
  }

  public Integer getSeasonNumber() {
      return seasonNumber;
  }

  public void setSeasonNumber(Integer seasonNumber) {
      this.seasonNumber = seasonNumber;
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

  @Override
  public String toString() {
      return "seasonNumber=" + seasonNumber +
              ", title='" + title + '\'' +
              ", episodeNumber=" + episodeNumber +
              ", ratings=" + ratings +
              ", releaseDate=" + releaseDate;
  }
}

