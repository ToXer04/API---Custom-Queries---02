package develhope.exercise.Exercise.Spring.Boot.Custom.Queries2.services;

import develhope.exercise.Exercise.Spring.Boot.Custom.Queries2.entities.Flight;
import develhope.exercise.Exercise.Spring.Boot.Custom.Queries2.entities.FlightStatus;
import develhope.exercise.Exercise.Spring.Boot.Custom.Queries2.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class FlightService {
    @Autowired
    FlightRepository flightRepository;

    private String generateRandomString() {
        Random random = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            char randomChar = alphabet.charAt(random.nextInt(alphabet.length()));
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }

    private FlightStatus generateRandomStatus() {
        Random random = new Random();
        int randomInt = random.nextInt(1,4);
        return switch (randomInt) {
            case 1 -> FlightStatus.ONTIME;
            case 2 -> FlightStatus.DELAYED;
            case 3 -> FlightStatus.CANCELLED;
            default -> null;
        };
    }

    public void generateRandomFlights(int n) {
        List<Flight> flightList = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            Flight randomFlight = new Flight();
            randomFlight.setDescription(generateRandomString());
            randomFlight.setFromAirport(generateRandomString());
            randomFlight.setToAirport(generateRandomString());
            randomFlight.setStatus(generateRandomStatus());
            flightList.add(randomFlight);
        }
        flightRepository.saveAll(flightList);
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }
    public Page<Flight> findAllSorted() {
        Pageable pageSortAsc = PageRequest.of(0, 5, Sort.by("fromAirport").ascending());
        return flightRepository.findAll(pageSortAsc);
    }

    public List<Flight> findOnTime() {
        List<Flight> allFlights = findAll();
        List<Flight> onTimeFlights = new ArrayList<>();

        for (Flight f : allFlights) {
            if (f.getStatus() == FlightStatus.ONTIME) {
                onTimeFlights.add(f);
            }
        }
        return onTimeFlights;
    }
    public List<Flight> findByStatus(String p1, String p2){
        return flightRepository.findByStatus(p1, p2);
    }
}

