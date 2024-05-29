package org.selfproject.cinema_app.controller;

import org.selfproject.cinema_app.model.BiletAlEntity;
import org.selfproject.cinema_app.repository.SeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/seats")
public class SeatsController {

    SeatsController(SeatsRepository seatsRepository) {
        this.seatsRepository = seatsRepository;
    }
    private SeatsRepository seatsRepository;

    @PutMapping("/save")
    public ResponseEntity<BiletAlEntity> updateSeatsSelection(@RequestBody BiletAlEntity biletAlEntity) {
        try {
            System.out.println(biletAlEntity.toString());
            // Retrieve the latest entity (assuming the latest added entity has the highest ID)
            Optional<BiletAlEntity> optionalEntity = seatsRepository.findTopByOrderByIdDesc();
            if (optionalEntity.isPresent()) {
                BiletAlEntity existingEntity = optionalEntity.get();

                // Update only the fields that are not null
                if (biletAlEntity.getSecilenKoltuklar() != null) {
                    existingEntity.setSecilenKoltuklar(biletAlEntity.getSecilenKoltuklar());
                }
                if (biletAlEntity.getSecilenSinema() != null) {
                    existingEntity.setSecilenSinema(biletAlEntity.getSecilenSinema());
                }
                if (biletAlEntity.getSecilenTarih() != null) {
                    existingEntity.setSecilenTarih(biletAlEntity.getSecilenTarih());
                }
                if (biletAlEntity.getSecilenSeans() != null) {
                    existingEntity.setSecilenSeans(biletAlEntity.getSecilenSeans());
                }
                if (biletAlEntity.getOgrenciBiletSayisi() != null) {
                    existingEntity.setOgrenciBiletSayisi(biletAlEntity.getOgrenciBiletSayisi());
                }
                if (biletAlEntity.getTamBiletSayisi() != null) {
                    existingEntity.setTamBiletSayisi(biletAlEntity.getTamBiletSayisi());
                }

                // Save the updated entity
                BiletAlEntity updatedEntity = seatsRepository.save(existingEntity);
                return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
            } else {
                // No entities found
                System.out.println(biletAlEntity.toString());
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public List<BiletAlEntity> getAllSeatsSelections() {
        return seatsRepository.findAll();
    }
}