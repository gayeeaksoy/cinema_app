package org.selfproject.cinema_app.controller;

import org.selfproject.cinema_app.model.UserEntity;
import org.selfproject.cinema_app.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class UserController {

    private final UserRepository userRepository;
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping("/api/users")
    public ResponseEntity<UserEntity> userSignup(@RequestBody UserEntity userEntity){
        return new ResponseEntity<>(userRepository.save(userEntity), HttpStatus.CREATED);
    }

    @PutMapping("/api/users")
    public ResponseEntity<UserEntity> AddtoFavorite(@RequestBody UserEntity userEntity){
        return new ResponseEntity<>(userRepository.save(userEntity), HttpStatus.CREATED);
    }












}
