package hu.progmasters.dinopark.controller;

import hu.progmasters.dinopark.dto.VisitorCreate;
import hu.progmasters.dinopark.dto.VisitorInfo;
import hu.progmasters.dinopark.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitor")
public class VisitorController {

    private final VisitorService visitorService;

    @Autowired
    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @PostMapping
    public ResponseEntity<VisitorInfo> save(@RequestBody VisitorCreate  create) {
        VisitorInfo saved = visitorService.save(create);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VisitorInfo>> findAll() {
        List<VisitorInfo> visitorInfos = visitorService.findAll();
        return new ResponseEntity<>(visitorInfos, HttpStatus.OK);
    }
}
