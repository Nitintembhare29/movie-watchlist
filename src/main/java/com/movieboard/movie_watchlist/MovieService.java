package com.movieboard.movie_watchlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepositorys;

    public String addMovie(Movie movie){
       return  movieRepositorys.saveMovie(movie);
    }

    public String addDirector(Director director){
        return movieRepositorys.saveDirector(director);
    }

    public void createMovieDirectorPair(String director, String movie){
        movieRepositorys.saveDirectorMoviePair(director, movie);
    }

    public Movie findMovie(String movieName){
        return movieRepositorys.findMovie(movieName);
    }

    public Director findDirector(String directorName){
        return movieRepositorys.findDirector(directorName);
    }

    public List<String> getAllMovies(){
        return movieRepositorys.getAllMovies();
    }

    public List<String> getMoviesByDirectorName(String dir){
        return movieRepositorys.getMoviesByDirectorName(dir);
    }

    public String deleteDirectorByName(String dir){
        return movieRepositorys.deleteDirectorByName(dir);
    }
}
