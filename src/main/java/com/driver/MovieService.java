package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Component
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public void addMovie(Movie movie){
        movieRepository.addMovie(movie);
    }

    public void addDirector(Director director){
        movieRepository.addDirector(director);
    }

    public void addMovieDirector(String movie,String director){
        movieRepository.addMovieDirectorPair(movie,director);
    }

    public Movie getMovieByName(String movie){
        return movieRepository.getMovieByName(movie);
    }

    public Director getDirectorByName(String director){
        return movieRepository.getDirectorByName(director);
    }

    public List<String> getMoviesByDirectorName(String directorName){
        return movieRepository.getMoviesByDirectorName(directorName);
    }

    public List<String> findAllMovies(){
        return movieRepository.findAllMovies();
    }

    public void deleteDirectorByName(String directorName){
        movieRepository.deleteDirectorByName(directorName);
    }

    public void deleteAllDirectors(){
        movieRepository.deleteAllDirectors();
    }

}
