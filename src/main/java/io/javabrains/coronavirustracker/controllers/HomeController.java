package io.javabrains.coronavirustracker.controllers;
import io.javabrains.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



//@Controller annotation helps the application identify the class as a controller.
@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    //@GetMapping annotation specifies that the method only handles GET HTTP requests.
    @GetMapping("/")
    public String home(Model model) {

        //Retrieve data from the getter methods within coronaVirusDataService.
        int cases = coronaVirusDataService.getCases();
        int deaths = coronaVirusDataService.getDeaths();
        int recovered = coronaVirusDataService.getRecovered();
        int todayRecovered = coronaVirusDataService.getTodayRecovered();
        int active = coronaVirusDataService.getActive();
        int critical = coronaVirusDataService.getCritical();

        //Add necessary information for the webpage in the controller.
        model.addAttribute("cases", cases);
        model.addAttribute("deaths", deaths);
        model.addAttribute("recovered", recovered);
        model.addAttribute("todayRecovered", todayRecovered);
        model.addAttribute("active", active);
        model.addAttribute("critical", critical);

        //Return "home" because the method directs the webpage to the index.html.
        return "index";
    }
}
