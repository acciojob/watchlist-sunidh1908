package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Component

public class MovieRepository {

    Map<String,Movie> listOfMovies = new HashMap<>();
    Map<String,Director> listOfDirector = new HashMap<>();
    Map<Movie,Director> directorMovieList = new HashMap<>();

    public void addMovie(Movie movie){
        listOfMovies.put(movie.getName(),movie);
    }

    public void addDirector(Director director){
        listOfDirector.put(director.getName(),director);
    }

    public void addMovieDirectorPair(String movie,String director){
        directorMovieList.put(listOfMovies.get(movie),listOfDirector.get(director));
    }

    public Movie getMovieByName(String movieName){
        for(Movie movie : listOfMovies.values()){
            if(movie.getName().equals(movieName)) return movie;
        }
        return new Movie();
    }

    public Director getDirectorByName(String directorName){
        return listOfDirector.get(directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName){
        List<String> moviesOfDirector = new ArrayList<>();
        for(Movie movie : directorMovieList.keySet()){
            if (directorMovieList.get(movie).getName().equals(directorName)){
                moviesOfDirector.add(movie.getName());
            }
        }
        return moviesOfDirector;
    }

    public List<String> findAllMovies(){
        List<String> allMovies = new ArrayList<>();
        for(Movie movies : listOfMovies.values()){
            allMovies.add(movies.getName());
        }
        return allMovies;
    }

    public void deleteDirectorByName(String directorName){
        for(Movie movie : directorMovieList.keySet()){
            if(directorMovieList.get(movie).getName().equals(directorName)){
                directorMovieList.remove(movie);
                listOfMovies.remove(movie.getName());
            }
        }

        for(String director : listOfDirector.keySet()){
            if(director.equals(directorName)){
                listOfDirector.remove(director);
            }
        }
    }

    public void deleteAllDirectors(){
        for(Movie movie : directorMovieList.keySet()){
            directorMovieList.remove(movie.getName());
        }
        listOfDirector.clear();
    }
}
