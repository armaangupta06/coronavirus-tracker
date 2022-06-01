package io.javabrains.coronavirustracker.controllers;

import io.javabrains.coronavirustracker.models.LocationStats;
import io.javabrains.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        int cases = coronaVirusDataService.getCases();
        int deaths = coronaVirusDataService.getDeaths();
        int recovered = coronaVirusDataService.getRecovered();
        int todayRecovered = coronaVirusDataService.getTodayRecovered();
        int active = coronaVirusDataService.getActive();
        int critical = coronaVirusDataService.getCritical();

        model.addAttribute("cases", cases);
        model.addAttribute("deaths", deaths);
        model.addAttribute("recovered", recovered);
        model.addAttribute("todayRecovered", todayRecovered);
        model.addAttribute("active", active);
        model.addAttribute("critical", critical);
        return "home";
    }
}
