package io.javabrains.coronavirustracker.services;


import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class CoronaVirusDataService {

    //Create String variable to hold URL of the COVID API
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
        //Create HttpClient object to handle HTTP requests.
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();

        //Send HTTP Request to access data from the api
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        parse(response.body());

    }
    public void parse(String responseBody){
        //Encode response of HTTP request to JSON.
        JSONObject covidData = new JSONObject(responseBody);

        //Parse through JSON and organize data.
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
