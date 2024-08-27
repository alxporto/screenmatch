package com.datalastix.screenmatch;

import com.datalastix.screenmatch.model.DataSeries;
import com.datalastix.screenmatch.service.APIConsumption;
import com.datalastix.screenmatch.service.DataConversion;
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
        var json = apiConsumption.getData("https://www.omdbapi.com/?t=gilmore+girls&Season=1&apikey=bcf7aa10");
        System.out.println(json);
        DataConversion converter = new DataConversion();
        DataSeries data = converter.getData(json, DataSeries.class);
        System.out.println(data);
    }
}
