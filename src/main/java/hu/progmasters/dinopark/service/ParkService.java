package hu.progmasters.dinopark.service;

import hu.progmasters.dinopark.dto.VisitorInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkService {

    private final VisitorService visitorService;

    @Autowired
    public ParkService(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    public double getAvgRating() {
        return visitorService.findAll().stream()
                .mapToInt(VisitorInfo::getRating)
                .average().orElse(0);
    }
}
