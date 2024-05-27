package org.selfproject.cinema_app.controller;

import org.selfproject.cinema_app.model.BiletAlEntity;
import org.selfproject.cinema_app.repository.BiletAlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api/biletal")
public class BiletAlController {

    @Autowired
    private BiletAlRepository biletAlRepository;

    @PostMapping
    public ResponseEntity<BiletAlEntity> saveSelection(@RequestBody BiletAlEntity biletAlEntity) {
        try {
            BiletAlEntity savedEntity = biletAlRepository.save(biletAlEntity);
            return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public List<BiletAlEntity> getAllSelections() {
        return biletAlRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BiletAlEntity> getBiletAlById(@PathVariable Long id) {
        BiletAlEntity biletAl = biletAlRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BiletAlEntity not found with id: " + id));
        return ResponseEntity.ok(biletAl);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BiletAlEntity> updateBiletAl(@PathVariable Long id, @RequestBody BiletAlEntity biletAlDetails) {
        BiletAlEntity biletAl = biletAlRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BiletAlEntity not found with id: " + id));

        biletAl.setSecilenSinema(biletAlDetails.getSecilenSinema());
        biletAl.setSecilenTarih(biletAlDetails.getSecilenTarih());
        biletAl.setSecilenSeans(biletAlDetails.getSecilenSeans());
        biletAl.setOgrenciBiletSayisi(biletAlDetails.getOgrenciBiletSayisi());
        biletAl.setTamBiletSayisi(biletAlDetails.getTamBiletSayisi());

        BiletAlEntity updatedBiletAl = biletAlRepository.save(biletAl);
        return ResponseEntity.ok(updatedBiletAl);
    }


}