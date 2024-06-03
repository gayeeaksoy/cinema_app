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

    private SeatsRepository seatsRepository;

    SeatsController(SeatsRepository seatsRepository) {
        this.seatsRepository = seatsRepository;
    }

    @PutMapping("/save")
    public ResponseEntity<BiletAlEntity> updateSeatsSelection(@RequestBody BiletAlEntity biletAlEntity) {
        try {
            System.out.println(biletAlEntity.toString());
            Optional<BiletAlEntity> optionalEntity = seatsRepository.findTopByOrderByIdDesc();
            optionalEntity.ifPresent(existingEntity -> {
                Optional.ofNullable(biletAlEntity.getSecilenKoltuklar()).ifPresent(existingEntity::setSecilenKoltuklar);
                Optional.ofNullable(biletAlEntity.getSecilenSinema()).ifPresent(existingEntity::setSecilenSinema);
                Optional.ofNullable(biletAlEntity.getSecilenTarih()).ifPresent(existingEntity::setSecilenTarih);
                Optional.ofNullable(biletAlEntity.getSecilenSeans()).ifPresent(existingEntity::setSecilenSeans);
                Optional.ofNullable(biletAlEntity.getOgrenciBiletSayisi()).ifPresent(existingEntity::setOgrenciBiletSayisi);
                Optional.ofNullable(biletAlEntity.getTamBiletSayisi()).ifPresent(existingEntity::setTamBiletSayisi);
                seatsRepository.save(existingEntity);
            });
            return optionalEntity.map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public List<BiletAlEntity> getAllSeatsSelections() {
        return seatsRepository.findAll();
    }
}