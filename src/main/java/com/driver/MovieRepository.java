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
    Map<String,List<String>> directorMovieList = new HashMap<>();

    public void addMovie(Movie movie){
        listOfMovies.put(movie.getName(),movie);
    }

    public void addDirector(Director director){
        listOfDirector.put(director.getName(),director);
    }

    public void addMovieDirectorPair(String movie,String director){
        if (listOfMovies.containsKey(movie) && listOfDirector.containsKey(director)) {
            listOfMovies.put(movie, listOfMovies.get(movie));
            listOfDirector.put(director, listOfDirector.get(director));
            List<String> currMovies = new ArrayList<>();
            if(directorMovieList.containsKey(director)){
                currMovies = directorMovieList.get(director);
            }
            currMovies.add(movie);
            directorMovieList.put(director,currMovies);
        }
    }

    public Movie getMovieByName(String movieName){
        return listOfMovies.get(movieName);
    }

    public Director getDirectorByName(String directorName){
        return listOfDirector.get(directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName){
        List<String> moviesOfDirector = new ArrayList<>();
        if(directorMovieList.containsKey(directorName)){
            moviesOfDirector = directorMovieList.get(directorName);
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
        List<String> directors = new ArrayList<>();
        if(listOfDirector.containsKey(directorName)){
            listOfDirector.remove(directorName);
        }
        if(directorMovieList.containsKey(directorName)){
            directors = directorMovieList.get(directorName);
            for(String movie : directors){
                if(listOfMovies.containsKey(movie)){
                    listOfMovies.remove(movie);
                }
            }
            directorMovieList.remove(directorName);
        }
    }

    public void deleteAllDirectors(){
        listOfDirector.clear();

        for(String director : directorMovieList.keySet()){
            directorMovieList.remove(director);
            directorMovieList.remove(directorMovieList.get(director));
        }
    }
}
