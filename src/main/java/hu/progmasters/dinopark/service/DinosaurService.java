package hu.progmasters.dinopark.service;

import hu.progmasters.dinopark.domain.Diet;
import hu.progmasters.dinopark.domain.Dinosaur;
import hu.progmasters.dinopark.dto.DinosaurCreate;
import hu.progmasters.dinopark.dto.DinosaurInfo;
import hu.progmasters.dinopark.repository.DinosaurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DinosaurService {

    private DinosaurRepository dinosaurRepository;

    @Autowired
    public DinosaurService(DinosaurRepository dinosaurRepository) {
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

    public List<DinosaurInfo> findAllByDiet(String diet) throws IllegalArgumentException {
        return dinosaurRepository.findAllByDiet(Diet.valueOf(diet)).stream()
                .map(this::convertToDinosaurInfo)
                .collect(Collectors.toList());
    }

    private Dinosaur convertToDinosaur(DinosaurCreate create) {
        return new Dinosaur()
                .setName(create.getName())
                .setBreed(create.getBreed())
                .setDiet(Diet.valueOf(create.getDiet().toUpperCase()));
    }

    private DinosaurInfo convertToDinosaurInfo(Dinosaur dinosaur) {
        return new DinosaurInfo()
                .setId(dinosaur.getId())
                .setName(dinosaur.getName())
                .setBreed(dinosaur.getBreed())
                .setDiet(dinosaur.getDiet().name().toLowerCase());
    }
}
