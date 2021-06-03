package hu.progmasters.dinopark.controller;

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
        if(dinosaurInfo != null) {
            return new ResponseEntity<>(dinosaurInfo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<DinosaurInfo>> findAll() {
        List<DinosaurInfo> dinosaurInfos = dinosaurService.findAll();
        return new ResponseEntity<>(dinosaurInfos, HttpStatus.OK);
    }

    @GetMapping("/{diet}")
    public ResponseEntity<List<DinosaurInfo>> findAllByDiet(@PathVariable String diet) {
        try{
            List<DinosaurInfo> dinosaurInfos = dinosaurService.findAllByDiet(diet);
            return new ResponseEntity<>(dinosaurInfos, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
