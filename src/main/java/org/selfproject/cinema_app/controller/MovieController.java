package org.selfproject.cinema_app.controller;

import org.selfproject.cinema_app.model.MovieEntity;
import org.selfproject.cinema_app.repository.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins="http://localhost:3000")
@RestController
public class MovieController {


    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }



    @PostMapping("/api/movies")
    public ResponseEntity<MovieEntity> postMovie(@RequestBody MovieEntity movieEntity){
        return new ResponseEntity<>(movieRepository.save(movieEntity), HttpStatus.CREATED);
    }


    @PutMapping("/api/movies/{id}")
    public ResponseEntity<MovieEntity> updateMovie(@PathVariable String name, @RequestBody MovieEntity movieEntity){
        MovieEntity optionalMovieEntity = movieRepository.findByName(name);
        if(optionalMovieEntity != null){
            MovieEntity existingMovieEntity = optionalMovieEntity;

            existingMovieEntity.setName(movieEntity.getName());
            existingMovieEntity.setDuration(movieEntity.getDuration());
            existingMovieEntity.setGenre(movieEntity.getGenre());
            existingMovieEntity.setRating(movieEntity.getRating());
            existingMovieEntity.setCast(movieEntity.getCast());
            existingMovieEntity.setDirector(movieEntity.getDirector());
            existingMovieEntity.setImageLocation(movieEntity.getImageLocation());
            existingMovieEntity.setReleaseDate(movieEntity.getReleaseDate());

            MovieEntity updatedMovieEntity = movieRepository.save(existingMovieEntity);
            return new ResponseEntity<>(updatedMovieEntity,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/api/movies/{name}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String name){
        MovieEntity movieEntity = movieRepository.findByName(name);
        if (movieEntity != null) {
            movieRepository.delete(movieEntity);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
