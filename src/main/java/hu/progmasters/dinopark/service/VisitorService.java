package hu.progmasters.dinopark.service;

import hu.progmasters.dinopark.domain.DinosaurDietType;
import hu.progmasters.dinopark.domain.Visitor;
import hu.progmasters.dinopark.dto.DinosaurInfo;
import hu.progmasters.dinopark.dto.VisitorCreate;
import hu.progmasters.dinopark.dto.VisitorInfo;
import hu.progmasters.dinopark.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitorService {

    private final DinosaurService dinosaurService;

    private final VisitorRepository visitorRepository;

    @Autowired
    public VisitorService(DinosaurService dinosaurService, VisitorRepository visitorRepository) {
        this.dinosaurService = dinosaurService;
        this.visitorRepository = visitorRepository;
    }

    public VisitorInfo save(VisitorCreate create) {
        Visitor visitor = convertToVisitor(create);
        List<DinosaurInfo> dinosaurInfos = dinosaurService.findAll();
        visitor.setRating(dinosaurInfos);

        Visitor saved = visitorRepository.save(visitor);

        return convertToVisitorInfo(saved);
    }

    public List<VisitorInfo> findAll() {
        return visitorRepository.findAll().stream()
                .map(this::convertToVisitorInfo)
                .collect(Collectors.toList());
    }

    private Visitor convertToVisitor(VisitorCreate create) {
        return new Visitor()
                .setName(create.getName())
                .setPreferred(DinosaurDietType.valueOf(create.getPreferredDinoDietType().toUpperCase()));
    }

    private VisitorInfo convertToVisitorInfo(Visitor visitor) {
        return new VisitorInfo()
                .setName(visitor.getName())
                .setRating(visitor.getRating());
    }
}
