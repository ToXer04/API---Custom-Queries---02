package develhope.exercise.Exercise.Spring.Boot.Custom.Queries2.controllers;

import develhope.exercise.Exercise.Spring.Boot.Custom.Queries2.entities.Flight;
import develhope.exercise.Exercise.Spring.Boot.Custom.Queries2.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @PostMapping("/provision")
    public void provision(
            @RequestParam(required = false, defaultValue = "100") int n
    ) {
        flightService.generateRandomFlights(n);
    }
    @GetMapping("/get-all")
    public List<Flight> getAll(){
        return flightService.findAll();
    }
    @GetMapping("/get-all-sorted")
    public Page<Flight> getAllSorted(){
        return flightService.findAllSorted();
    }
    @GetMapping("/get-on-time")
    public List<Flight> getOnTime(){
        return flightService.findOnTime();
    }
    @GetMapping("/get-by-status")
    public List<Flight> getByStatus(@RequestParam String p1,@RequestParam String p2){
        return flightService.findByStatus(p1,p2);
    }
}
