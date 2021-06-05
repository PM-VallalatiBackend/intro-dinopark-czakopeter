package hu.progmasters.dinopark.service;

import hu.progmasters.dinopark.domain.DinosaurType;
import hu.progmasters.dinopark.domain.Dinosaur;
import hu.progmasters.dinopark.dto.DinosaurCreate;
import hu.progmasters.dinopark.dto.DinosaurInfo;
import hu.progmasters.dinopark.repository.DinosaurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DinosaurService {

    private final DinosaurRepository dinosaurRepository;

    @Autowired
    public DinosaurService(
            @Qualifier("dinosaurRepositoryH2JdbcTemplate") DinosaurRepository dinosaurRepository) {
        this.dinosaurRepository = dinosaurRepository;
    }

    public DinosaurInfo save(DinosaurCreate dinosaurCreate) {
        Dinosaur dinosaur = convertToDinosaur(dinosaurCreate);
        Dinosaur saved = dinosaurRepository.save(dinosaur);
        return convertToDinosaurInfo(saved);
    }

    public List<DinosaurInfo> findAll() {
        return dinosaurRepository.findAll().stream()
                .map(this::convertToDinosaurInfo)
                .collect(Collectors.toList());
    }

    public List<DinosaurInfo> findAllByType(String type) throws IllegalArgumentException {
        return dinosaurRepository.findAllByType(DinosaurType.valueOf(type.toUpperCase())).stream()
                .map(this::convertToDinosaurInfo)
                .collect(Collectors.toList());
    }

    private Dinosaur convertToDinosaur(DinosaurCreate create) {
        return new Dinosaur()
                .setName(create.getName())
                .setBreed(create.getBreed())
                .setType(DinosaurType.valueOf(create.getType().toUpperCase()));
    }

    private DinosaurInfo convertToDinosaurInfo(Dinosaur dinosaur) {
        return new DinosaurInfo()
                .setId(dinosaur.getId())
                .setName(dinosaur.getName())
                .setBreed(dinosaur.getBreed())
                .setType(dinosaur.getType().name().toLowerCase());
    }
}
