package org.selfproject.cinema_app.controller;

import org.selfproject.cinema_app.model.UserEntity;
import org.selfproject.cinema_app.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/api/users/login")
    public ResponseEntity<?> userLogin(@RequestBody UserEntity userEntity) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(userEntity.getEmail());
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            if (user.getPassword().equals(userEntity.getPassword())) {
                System.out.println("Login successful: " + user.toString()); // Kullanıcıyı konsola yaz
                return ResponseEntity.ok(user); // Başarılı giriş
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }
    }

}
