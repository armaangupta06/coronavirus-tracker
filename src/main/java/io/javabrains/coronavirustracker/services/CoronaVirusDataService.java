package io.javabrains.coronavirustracker.services;

import io.javabrains.coronavirustracker.CoronavirusTrackerApplication;
import io.javabrains.coronavirustracker.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaVirusDataService {

    private static String VIRUS_DATA_URL = "https://corona.lmao.ninja/v2/all";

    private int cases;

    private int deaths;

    private int recovered;

    private int todayRecovered;

    private int active;

    private int critical;

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        parse(response.body());

    }
    public void parse(String responseBody){
        JSONObject covidData = new JSONObject(responseBody);
        int cases = covidData.getInt("cases");
        int deaths = covidData.getInt("deaths");
        int recovered = covidData.getInt("recovered");
        int todayRecovered = covidData.getInt("todayRecovered");
        int active = covidData.getInt("active");
        int critical = covidData.getInt("critical");

        this.cases = cases;
        this.deaths = deaths;
        this.recovered = recovered;
        this.todayRecovered = todayRecovered;
        this.active = active;
        this.critical = critical;
    }
    public int getCases(){
        return this.cases;
    }

    public int getDeaths(){
        return this.deaths;
    }

    public int getRecovered() {return this.recovered; }

    public int getTodayRecovered() {return this.todayRecovered; }

    public int getActive() {return this.active; }

    public int getCritical() {return this.critical; }
}
