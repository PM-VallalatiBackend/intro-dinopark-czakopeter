package hu.progmasters.dinopark.controller;

import hu.progmasters.dinopark.domain.DinosaurType;
import hu.progmasters.dinopark.dto.DinosaurCreate;
import hu.progmasters.dinopark.dto.DinosaurInfo;
import hu.progmasters.dinopark.service.DinosaurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dino")
public class DinosaurController {

    private final DinosaurService dinosaurService;

    @Autowired
    public DinosaurController(DinosaurService dinosaurService) {
        this.dinosaurService = dinosaurService;
    }

    @PostMapping
    public ResponseEntity<DinosaurInfo> save(@RequestBody DinosaurCreate dinosaurCreate) {
        DinosaurInfo dinosaurInfo = dinosaurService.save(dinosaurCreate);
        return dinosaurInfo != null
            ? new ResponseEntity<>(dinosaurInfo, HttpStatus.CREATED)
            : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<DinosaurInfo>> findAll() {
        List<DinosaurInfo> dinosaurInfos = dinosaurService.findAll();
        return new ResponseEntity<>(dinosaurInfos, HttpStatus.OK);
    }

    @GetMapping("/{type}")
    public ResponseEntity<List<DinosaurInfo>> findAllByType(@PathVariable String type) {
        return DinosaurType.contains(type)
            ? new ResponseEntity<>(dinosaurService.findAllByType(type), HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
