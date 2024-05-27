package org.selfproject.cinema_app.controller;

import org.selfproject.cinema_app.model.UserEntity;
import org.selfproject.cinema_app.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Optional;


@Component
@SessionScope
@CrossOrigin(origins="http://localhost:3000")
@RestController
public class UserController {

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    private final UserRepository userRepository;
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @PostMapping("/api/users")
    public ResponseEntity<UserEntity> userSignup(@RequestBody UserEntity userEntity){
        return new ResponseEntity<>(userRepository.save(userEntity), HttpStatus.CREATED);
    }

    @PostMapping("/api/users/login")
    public ResponseEntity<?> userLogin(@RequestBody UserEntity userEntity) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(userEntity.getEmail());
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            if (user.getPassword().equals(userEntity.getPassword())) {
                setUserId(user.getId());
                System.out.println("Login successful: " + user.toString()); // Kullanıcıyı konsola yaz
                return ResponseEntity.ok(user); // Başarılı giriş
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }
    }

    @PutMapping("/api/users/favorite/{movieId}")
    public ResponseEntity<UserEntity> addToFavorite(@RequestBody String movieId) {
        Long userId = getUserId(); // Assuming you have a method to retrieve the user ID
        UserEntity existingUser = userRepository.findById(userId).orElse(null);

        if (existingUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Append the movie to the favoriteList
        String favoriteList = existingUser.getFavoriteList();
        if (favoriteList == null || favoriteList.isEmpty()) {
            existingUser.setFavoriteList(movieId);
        } else {
            // Append a comma and the new movie to the existing favorite list
            existingUser.setFavoriteList(favoriteList + ", " + movieId);
        }

        // Save the updated user
        UserEntity savedUser = userRepository.save(existingUser);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }















}
