package com.movieboard.movie_watchlist;


import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("movies")
@Slf4j

public class MovieController {
    @Autowired
    MovieService movieServices;

    // http://localhost:8080/add_movie
    @PostMapping("/add_movie")
    public ResponseEntity addMovie(@RequestBody() Movie movie) {
         String response = movieServices.addMovie(movie);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // http://localhost:8080/add_director
    @PostMapping("/add_director")
    public ResponseEntity addDirector(@RequestBody() Director director) {
        String response = movieServices.addDirector(director);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/add_movie_director_pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movie") String movie, @RequestParam("director") String director){
        movieServices.createMovieDirectorPair(director, movie);
        return new ResponseEntity<>("new director movie pair created", HttpStatus.CREATED);
    }

    // http://localhost:8080/get_movie_by_name/Iron-man
    @GetMapping("/get_movie_by_name/{name}")
    public ResponseEntity getMovieByName(@PathVariable String name){
        Movie response =  movieServices.findMovie(name);
        return new ResponseEntity<>( response, HttpStatus.FOUND);
    }

    // http://localhost:8080/get_director_by_name/Rohit_Shetty
    @GetMapping("/get_director_by_name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable String name){
        Director response =  movieServices.findDirector(name);
        return new ResponseEntity<>( response, HttpStatus.FOUND);
    }

    //http://localhost:8080/get-all-movies
    @GetMapping("/get-all-movies")
    public ResponseEntity getAllMovies(){
        List<String> response = movieServices.getAllMovies();
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String dir){
        List<String> response = movieServices.getMoviesByDirectorName(dir);
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("director") String dir){
        String response = movieServices.deleteDirectorByName(dir);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
}