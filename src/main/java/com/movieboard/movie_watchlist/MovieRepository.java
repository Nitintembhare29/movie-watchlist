package com.movieboard.movie_watchlist;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
@Slf4j
public class MovieRepository {

    // movie_name, movie object
    private HashMap<String, Movie> movieMap;

    // director_name, director object
    private HashMap<String, Director> directorMap;

    // director_name, list of movies made by director
    private HashMap<String, List<String>> directorMovieMap;


    public MovieRepository( ) {
        this.movieMap = new HashMap<String, Movie>();
        this.directorMap = new HashMap<String, Director>();
        this.directorMovieMap = new HashMap<String, List<String>> ();
    }


    public String saveMovie(Movie movie){
        String name = movie.getName();
        if(movieMap.containsKey(name)){
            return "movie name already exist";
        }
        else{
            movieMap.put(name, movie);
            return "Movie added successfully";
        }
    }

    public String saveDirector(Director dir){
        String director = dir.getName();
        if(!directorMap.containsKey(director)){
            directorMap.put(director, dir);
        }
        return "Director added successfully";
    }

    public void saveDirectorMoviePair(String director, String movie){
        if(movieMap.containsKey(movie) && directorMap.containsKey(director)){
            List<String> movieList = new ArrayList<>();
            if(directorMovieMap.containsKey(director)){
                movieList = directorMovieMap.get(director);
            }
            movieList.add(movie);
            directorMovieMap.put(director, movieList);
        }
    }

    public Movie findMovie(String movie){
        return movieMap.get(movie);
    }

    public Director findDirector(String director){
        return directorMap.get(director);
    }

    public List<String> getAllMovies(){
        return new ArrayList<>(movieMap.keySet());
    }

    public List<String> getMoviesByDirectorName(String dir){
        return directorMovieMap.get(dir);
    }

    public String deleteDirectorByName(String dir){
        // first get song created by director and remove all movies from movieMap


        if(directorMap.containsKey(dir)){
            directorMap.remove(dir);

            List<String> movieList = directorMovieMap.get(dir);

            // remove movies created by director
            for(String movie : movieList){
                if(movieMap.containsKey(movie)){
                    movieMap.remove(movie);
                }
            }
        }

        log.info("Error in Delete");
    return "Director removed successfully";

    }

}
