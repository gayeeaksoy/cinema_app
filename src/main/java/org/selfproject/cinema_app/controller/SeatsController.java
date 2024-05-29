package org.selfproject.cinema_app.controller;

import org.selfproject.cinema_app.model.BiletAlEntity;
import org.selfproject.cinema_app.repository.SeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/seats")
public class SeatsController {

    @Autowired
    private SeatsRepository seatsRepository;

    @PostMapping("/save")
    public ResponseEntity<BiletAlEntity> saveSeatsSelection(@RequestBody BiletAlEntity biletAlEntity) {
        try {
            BiletAlEntity savedEntity = seatsRepository.save(biletAlEntity);
            return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public List<BiletAlEntity> getAllSeatsSelections() {
        return seatsRepository.findAll();
    }
}