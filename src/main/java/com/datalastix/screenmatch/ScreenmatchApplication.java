package com.datalastix.screenmatch;

import com.datalastix.screenmatch.models.DataSeries;
import com.datalastix.screenmatch.services.APIConsumption;
import com.datalastix.screenmatch.services.ConvertData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var apiConsumption = new APIConsumption();
		var json = apiConsumption.getDataAPI("https://www.omdbapi.com/?t=fringe&apikey=bcf7aa10");
		System.out.println(json);
		ConvertData converter = new ConvertData();
		DataSeries data = converter.getDataJSON(json, DataSeries.class);
		System.out.println(data);
	}
}
