package com.datalastix.screenmatch.main;

import com.datalastix.screenmatch.model.DataEpisode;
import com.datalastix.screenmatch.model.DataSerie;
import com.datalastix.screenmatch.model.DataSeason;
import com.datalastix.screenmatch.model.Episode;
import com.datalastix.screenmatch.service.APIConsumption;
import com.datalastix.screenmatch.service.DataConversion;
import java.util.stream.Collectors;
import java.util.*;

public class Main {
    private Scanner reader = new Scanner(System.in);
    private APIConsumption consumption = new APIConsumption();
    private DataConversion converter = new DataConversion();
    private final String ADDRESS = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=bcf7aa10";

    public void showMenu() {
        System.out.print("Type the name of the series to be searched: ");
        var seriesName = reader.nextLine();
        var json = consumption.getData(ADDRESS + seriesName.replace(" ", "+") + API_KEY);
        DataSerie data = converter.getData(json, DataSerie.class);
        System.out.println(data);

        List<DataSeason> seasons = new ArrayList<>();

        for (int i = 1; i <= data.totalSeasons(); i++) {
            json = consumption.getData(ADDRESS +seriesName.replace(" ", "+") +"&season=" + i + API_KEY);
            DataSeason dataSeason = converter.getData(json, DataSeason.class);
            seasons.add(dataSeason);
        }
        seasons.forEach(System.out::println);

        seasons.forEach(season -> season.episodes().forEach(episode -> System.out.println(episode.title())));
        seasons.forEach(System.out::println);

        List<DataEpisode> dataEpisodes = seasons.stream()
                .flatMap(season -> season.episodes().stream())
                .collect(Collectors.toList());
//
//        System.out.println("\nTop 10 episodes:");
//        dataEpisodes.stream()
//                .filter(episode -> !episode.ratings().equalsIgnoreCase("N/A"))
//                .peek(episode -> System.out.println("First filter (N/A) " + episode))
//                .sorted(Comparator.comparing(DataEpisode::ratings).reversed())
//                .peek(episode -> System.out.println("Ordering " + episode))
//                .limit(10)
//                .peek(episode -> System.out.println("Limiting " + episode))
//                .map(episode -> episode.title().toUpperCase())
//                .peek(episode -> System.out.println("Mapping " + episode))
//                .forEach(System.out::println);

        List<Episode> episodes = seasons.stream()
                .flatMap(season -> season.episodes().stream()
                        .map(dataEpisode -> new Episode(season.seasonNumber(), dataEpisode)))
                .collect(Collectors.toList());

        episodes.forEach(System.out::println);

//        System.out.print("Enter an excerpt from the title of the desired episode: ");
//        var titleExcerpt = reader.nextLine();
//        Optional<Episode> searchedEpisode = episodes.stream()
//                .filter(episode -> episode.getTitle().toLowerCase().contains(titleExcerpt.toLowerCase()))
//                .findFirst();
//
//        if (searchedEpisode.isPresent()) {
//            System.out.println("Episode found!");
//            System.out.println("Season: " + searchedEpisode.get().getSeasonNumber());
//        } else {
//            System.out.println("Episode not found!");
//        }

        Map<Integer, Double> ratingsPerSeason = episodes.stream()
                .filter(episode -> episode.getRatings() > 0.0)
                .collect(Collectors.groupingBy(Episode::getSeasonNumber,
                         Collectors.averagingDouble(Episode::getRatings)));
        System.out.println(ratingsPerSeason);

        DoubleSummaryStatistics statistics = episodes.stream()
                .filter(episode -> episode.getRatings() > 0.0)
                .collect(Collectors.summarizingDouble(Episode::getRatings));
        System.out.printf("Average: %.2f \n", statistics.getAverage());
        System.out.printf("Best episode: %.2f \n", statistics.getMax());
        System.out.printf("Worst episode: %.2f \n", statistics.getMin());
        System.out.printf("Quantity: %d", statistics.getCount());

//        System.out.println("From since which year, do you want to watch the episodes?: ");
//        var year = reader.nextInt();
//        reader.nextLine();
//
//        LocalDate searchDate = LocalDate.of(year, 1, 1);
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        episodes.stream()
//                .filter(episode -> episode.getReleaseDate() != null && episode.getReleaseDate().isAfter(searchDate))
//                .sorted(Comparator.comparing(Episode::getReleaseDate))
//                .forEach(episode -> System.out.println(
//                        "Season: " + episode.getSeasonNumber() +
//                                " Episode: " + episode.getTitle() +
//                                    " Release date: " + episode.getReleaseDate().format(formatter)
//                ));
    }
}

